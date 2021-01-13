package org.tinygame.herostory.rank;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.async.AsyncOperationProcessor;
import org.tinygame.herostory.async.IAsyncOperation;
import org.tinygame.herostory.util.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * 排行榜服务
 */
public final class RankService {

    /**
     * 单例对象
     */
    private static final RankService RANK_SERVICE = new RankService();

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RankService.class);

    /**
     * 私有化构造器
     */
    private RankService(){}

    /**
     * 获取单例对象
     *
     * @return 排行榜服务
     */
    public static RankService getInstance(){
        return RANK_SERVICE;
    }

    /**
     * 获取排名列表
     * @param callback 回调函数
     */
    public void getRank(Function<List<RankItem>,Void> callback) {

        if (null != callback) {
            AsyncGetRank asyncGetRank = new AsyncGetRank(){
                @Override
                public void doFinish() {
                    callback.apply(this.getRankItemList());
                }
            };

            AsyncOperationProcessor.getInstance().process(asyncGetRank);
        }

    }

    /**
     * 异步方式获取排名
     */
    private class AsyncGetRank implements IAsyncOperation {

        /**
         * 排名列表
         */
        private List<RankItem> rankItemList = null;

        /**
         * 获取排名列表
         * @return 排名列表
         */
        public List<RankItem> getRankItemList() {
            return rankItemList;
        }

        @Override
        public void doAsync() {
            try (Jedis redis = RedisUtil.getJedis()) {
                if (null == redis) {
                    return;
                }
                // redis获取set集合
                Set<Tuple> valSet = redis.zrevrangeWithScores("Rank", 0, 9);

                rankItemList = new ArrayList<>();

                int rankId = 0;

                for (Tuple t : valSet) {
                    // 获取用户id
                    int userId = Integer.parseInt(t.getElement());

                    // 获取用户的基本信息
                    String jsonStr = redis.hget("User_" + userId, "BasicInfo");
                    if (null == jsonStr || jsonStr.isEmpty()) {
                        continue;
                    }

                    // 填充RankItem
                    RankItem newItem = new RankItem();
                    newItem.rankId = ++rankId;
                    newItem.userId = userId;
                    newItem.win = (int) t.getScore();

                    // json字符串转成json对象
                    JSONObject jsonObj = JSONObject.parseObject(jsonStr);

                    newItem.userName = jsonObj.getString("userName");
                    newItem.heroAvatar = jsonObj.getString("heroAvatar");

                    // RankItem对象加进排名列表
                    rankItemList.add(newItem);

                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(),e);
            }
        }
    }

    /**
     * 刷新排行榜
     * @param winnerId 赢家id
     * @param loserId 输家id
     */
    public void refreshRank(int winnerId, int loserId) {
        try (Jedis redis = RedisUtil.getJedis()) {
            // 增加用户的输赢次数
            Long win = redis.hincrBy("User_" + winnerId, "Win", 1);
            //Long lose = redis.hincrBy("User_" + loserId, "Lose", 1);

            //修改排行榜
            redis.zadd("Rank", win, String.valueOf(winnerId));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
