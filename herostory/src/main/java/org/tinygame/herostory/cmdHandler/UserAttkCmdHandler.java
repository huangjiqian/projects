package org.tinygame.herostory.cmdHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.Broadcaster;
import org.tinygame.herostory.model.User;
import org.tinygame.herostory.model.UserManager;
import org.tinygame.herostory.mq.MQProducer;
import org.tinygame.herostory.mq.VictorMsg;
import org.tinygame.herostory.msg.GameMsgProtocol;

/**
 * 用户攻击指令处理器
 */
public class UserAttkCmdHandler implements ICmdHandler<GameMsgProtocol.UserAttkCmd> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAttkCmdHandler.class);

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserAttkCmd cmd) {
        if (null == ctx ||
            null == cmd) {
            return;
        }

        // 获取攻击者 Id
        Integer attkUserId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();
        if (null == attkUserId) {
            return;
        }

        // 获取被攻击者 Id
        int targetUserId = cmd.getTargetUserId();

        GameMsgProtocol.UserAttkResult.Builder resultBuilder = GameMsgProtocol.UserAttkResult.newBuilder();
        resultBuilder.setAttkUserId(attkUserId);
        resultBuilder.setTargetUserId(targetUserId);

        GameMsgProtocol.UserAttkResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);

        // 获取被攻击用户
        User targetUser = UserManager.getUserById(targetUserId);

        if (null == targetUser) {
            return;
        }

        LOGGER.info("当前线程={}",Thread.currentThread().getName());

        int subtractHp = 10;
        targetUser.currHp -= subtractHp;

        // 广播减血消息
        broadcastSubtractHp(targetUserId,subtractHp);

        if (targetUser.currHp <= 0){
            // 广播死亡消息
            broadcastDie(targetUserId);

            if (!targetUser.dead) {
                targetUser.dead = true;
                // 填充消息结果
                VictorMsg mqMsg = new VictorMsg();
                mqMsg.winnerId = attkUserId;
                mqMsg.loserId = targetUserId;
                LOGGER.info("winnerId={},loserId={}", attkUserId, targetUserId);
                // 发生消息
                MQProducer.sendMsg("Victor", mqMsg);
            }
        }
    }

    /**
     * 广播减血消息
     * @param targetUserId：目标用户id
     * @param subtractHp：减血量
     */
    private static void broadcastSubtractHp(int targetUserId, int subtractHp){
        if (targetUserId <=0 || subtractHp <= 0){
            return;
        }
        GameMsgProtocol.UserSubtractHpResult.Builder resultBuilder = GameMsgProtocol.UserSubtractHpResult.newBuilder();
        resultBuilder.setTargetUserId(targetUserId);
        resultBuilder.setSubtractHp(subtractHp);

        GameMsgProtocol.UserSubtractHpResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);
    }

    /**
     * 广播死亡消息
     * @param targetUserId：目标用户id
     */
    private static void broadcastDie(int targetUserId) {
        if (targetUserId <= 0){
            return;
        }

        GameMsgProtocol.UserDieResult.Builder resultBuilder = GameMsgProtocol.UserDieResult.newBuilder();
        resultBuilder.setTargetUserId(targetUserId);
        GameMsgProtocol.UserDieResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);
    }
}
