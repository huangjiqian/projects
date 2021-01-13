package org.tinygame.herostory.cmdHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.login.LoginService;
import org.tinygame.herostory.login.db.UserEntity;
import org.tinygame.herostory.model.User;
import org.tinygame.herostory.model.UserManager;
import org.tinygame.herostory.msg.GameMsgProtocol;

public class UserLoginCmdHandler implements ICmdHandler<GameMsgProtocol.UserLoginCmd>{

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginCmdHandler.class);

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserLoginCmd cmd) {

        if (null == cmd || null == ctx) {
            return;
        }

        LOGGER.info("username = {}, password = {}",cmd.getUserName(),cmd.getPassword());

        LoginService.getInstance().userLogin(

                cmd.getUserName(),
                cmd.getPassword(),
                // 执行回调函数获取用户实体
                userEntity -> {

                    if (null == userEntity){
                        return null;
                    }

                    // 新建用户
                    User newUser = new User();
                    newUser.userId = userEntity.userId;
                    newUser.userName = userEntity.userName;
                    newUser.heroAvatar = userEntity.heroAvatar;
                    newUser.currHp = 100;
                    // 并将用户加入管理器
                    UserManager.addUser(newUser);

                    LOGGER.info("userName={}",userEntity.userName);

                    // 将用户 Id 附着到 Channel
                    ctx.channel().attr(AttributeKey.valueOf("userId")).set(newUser.userId);

                    // 登陆结果构建者
                    GameMsgProtocol.UserLoginResult.Builder resultBuilder =
                            GameMsgProtocol.UserLoginResult.newBuilder();
                    resultBuilder.setUserId(newUser.userId);
                    resultBuilder.setUserName(newUser.userName);
                    resultBuilder.setHeroAvatar(newUser.heroAvatar);


                    // 构建结果并发送
                    GameMsgProtocol.UserLoginResult newResult = resultBuilder.build();
                    ctx.writeAndFlush(newResult);
                    LOGGER.info("打印当前线程："+Thread.currentThread().getName());
                    return null;
                }
        );



    }
}
