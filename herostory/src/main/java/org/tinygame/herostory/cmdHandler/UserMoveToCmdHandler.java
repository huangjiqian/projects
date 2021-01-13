package org.tinygame.herostory.cmdHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.tinygame.herostory.Broadcaster;
import org.tinygame.herostory.model.MoveState;
import org.tinygame.herostory.model.User;
import org.tinygame.herostory.model.UserManager;
import org.tinygame.herostory.msg.GameMsgProtocol;

/**
 * 用户移动指令处理器
 */
public class UserMoveToCmdHandler implements ICmdHandler<GameMsgProtocol.UserMoveToCmd> {
    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserMoveToCmd cmd) {
        if (null == ctx
            || null == cmd) {
            return;
        }

        // 获取用户 Id
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();

        if (null == userId) {
            return;
        }
        
        User moveUser = UserManager.getUserById(userId);
        if (null == moveUser) {
            return;
        }

        // 获取移动状态
        MoveState moveState = moveUser.moveState;
        // 设置位置和开始时间
        moveState.fromPosX = cmd.getMoveFromPosX();
        moveState.fromPosY = cmd.getMoveFromPosY();
        moveState.toPosX = cmd.getMoveToPosX();
        moveState.toPosY = cmd.getMoveToPosY();
        // 必须设置成当前时间
        moveState.startTime = System.currentTimeMillis();

        // 将所有MoveState构建到UserMoveToResult
        GameMsgProtocol.UserMoveToResult.Builder resultBuilder = GameMsgProtocol.UserMoveToResult.newBuilder();
        resultBuilder.setMoveUserId(userId);
        resultBuilder.setMoveFromPosX(moveState.fromPosX);
        resultBuilder.setMoveFromPosY(moveState.fromPosY);
        resultBuilder.setMoveToPosX(moveState.toPosX);
        resultBuilder.setMoveToPosY(moveState.toPosY);
        resultBuilder.setMoveStartTime(moveState.startTime);

        GameMsgProtocol.UserMoveToResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);
    }
}
