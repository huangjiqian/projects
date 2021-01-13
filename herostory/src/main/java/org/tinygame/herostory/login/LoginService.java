package org.tinygame.herostory.login;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.MySqlSessionFactory;
import org.tinygame.herostory.async.AsyncOperationProcessor;
import org.tinygame.herostory.async.IAsyncOperation;
import org.tinygame.herostory.login.db.IUserDao;
import org.tinygame.herostory.login.db.UserEntity;
import org.tinygame.herostory.util.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.function.Function;

/**
 * 登陆服务
 */
public class LoginService {

    /**
     * 单例对象
     */
    private static final LoginService LOGIN_SERVICE = new LoginService();

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    /**
     * 私有化默认构造器
     */
    private LoginService(){}

    /**
     * 获取单例对象
     * @return 单例对象
     */
    public static LoginService getInstance(){
        return LOGIN_SERVICE;
    }

    /**
     * 用户登陆服务
     * @param userName 用户名
     * @param password 密码
     * @param callback 回调函数
     * @return 用户实体
     */
    public void userLogin(String userName,String password, Function<UserEntity,Void> callback){

        if ((null == userName || null == password) || ("".equals(userName) && "".equals(password))){
            LOGGER.error("用户名或者密码为空,username={} and password={}", userName, password);
            return;
        }

        AsyncGetUserByName asyncOp = new AsyncGetUserByName(userName, password){

            /**
             * MainThreadProcessor 会执行此方法获取回调函数内的用户实体
             */
            @Override
            public void doFinish() {
                if (null != callback) {
                    // 把用户实体加入回调函数内
                    callback.apply(this.getUserEntity());
                }
            }
        };

        // 执行异步操作
        AsyncOperationProcessor.getInstance().process(asyncOp);
    }

    /**
     * 更新 redis 用户中基本信息
     * @param userEntity 用户实体
     */
    private void UpdateUserBasicInfoInRedis(UserEntity userEntity) {
        if (null == userEntity || userEntity.userId < 0) {
            return;
        }

        try (Jedis redis = RedisUtil.getJedis()) {
            // 获取用户id
            int userId = userEntity.userId;

            // 创建 JSONObject 对象
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("userId",userId);
            jsonObj.put("userName",userEntity.userName);
            jsonObj.put("heroAvatar",userEntity.heroAvatar);

            LOGGER.info("UpdateUserBasicInfoInRedis.userName={}",userEntity.userName);

            // 更新redis数据
            redis.hset("User_" + userId, "BasicInfo", jsonObj.toJSONString());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
    

    /**
     * 自定义异步操作类
     */
    private class AsyncGetUserByName implements IAsyncOperation {

        /**
         * 用户名
         */
        private String userName;

        /**
         * 密码
         */
        private String password;

        /**
         * 用户实体
         */
        private UserEntity userEntity = null;

        public AsyncGetUserByName(String userName, String password){
            if (null == userName || null == password){
                LOGGER.error("用户名或者密码为空,username={} and password={}", userName, password);
                throw new IllegalArgumentException();
            }
            this.userName = userName;
            this.password = password;
        }

        /**
         * 获取用户实体
         * @return 用户实体
         */
        public UserEntity getUserEntity(){
            return userEntity;
        }

        /**
         * 指定用户名最后一个字符为绑定id
         * @return 绑定id
         */
        @Override
        public int bindId() {
            return userName.charAt(userName.length() - 1);
        }

        /**
         * 异步登陆用户
         */
        @Override
        public void doAsync() {
            userEntity = new UserEntity();
            // try-catch 结束后自动关闭sqlSession
            try (SqlSession mySqlSession = MySqlSessionFactory.openSession()) {
                // 获取DAO
                IUserDao userDao = mySqlSession.getMapper(IUserDao.class);
                // 获取用户实体
                userEntity = userDao.getUserByName(userName);

                if (null != userEntity) {
                    if (!password.equals(userEntity.password)) {
                        LOGGER.error("用户密码错误，username={}",userName);
                        throw new RuntimeException("用户密码错误");
                    }
                }else {
                    // 创建新的用户实体
                    userEntity = new UserEntity();
                    userEntity.userName = userName;
                    userEntity.password = password;
                    userEntity.heroAvatar = "Hero_Shaman"; //默认使用萨满


                    LOGGER.info("AsyncGetUserByName.userName={}",userName);


                    // 将用户加入数据库
                    userDao.insertInto(userEntity);

                }


                LoginService.getInstance().UpdateUserBasicInfoInRedis(userEntity);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(),e);
            }
        }
    }
}
