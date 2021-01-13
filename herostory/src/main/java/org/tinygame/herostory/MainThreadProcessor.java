package org.tinygame.herostory;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.cmdHandler.CmdHandlerFactory;
import org.tinygame.herostory.cmdHandler.ICmdHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主线程处理器。为了简单化业务逻辑，主线程变为单线程，不会有多线程下的各种问题
 *
 * @author 1378986
 */
public final class MainThreadProcessor {

    /**
     * 单例对象
     */
    private static final MainThreadProcessor INSTANCE = new MainThreadProcessor();

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainThreadProcessor.class);

    /**
     * 获取主线程处理器的单实例
     *
     */
    public static MainThreadProcessor getInstance() {
        return INSTANCE;
    }

    /**
     * 主线程为单线程
     */
    private static final ExecutorService MAIN_SIMPLE_THREAD =
            Executors.newSingleThreadExecutor((newRunnable) -> {
                Thread newThread = new Thread(newRunnable);
                newThread.setName("MainThreadProcessor");
                return newThread;
            });

    /**/

    /**
     * 私有化构造器
     */
    private MainThreadProcessor() {
    }

    /**
     * 主线程方法
     *
     * @param ctx：信道处理器
     * @param msg：消息对象
     */
    public void process(ChannelHandlerContext ctx, Object msg) {

        if (null == ctx || null == msg) {
            return;
        }

        MAIN_SIMPLE_THREAD.execute(() -> {
            // 获取消息类
            Class<?> msgClazz = msg.getClass();

            LOGGER.info(
                    "收到客户端消息, msgClazz = {}, msg = {}",
                    msgClazz.getName(),
                    msg
            );
            // 获取指令处理器
            ICmdHandler<? extends GeneratedMessageV3>
                    cmdHandler = CmdHandlerFactory.create(msgClazz);

            if (null == cmdHandler) {
                LOGGER.error(
                        "未找到相对应的指令处理器, msgClazz = {}",
                        msgClazz.getName()
                );
                return;
            }

            // 处理指令
            cmdHandler.handle(ctx, cast(msg));
        });

    }

    /**
     * 执行当前线程
     * @param newRunnable Runnable对象
     */
    public void process(Runnable newRunnable) {
        if (null == newRunnable) {
            return;
        }
        MAIN_SIMPLE_THREAD.submit(newRunnable);
    }

    /**
     * 转型消息对象
     *
     * @param msg    消息对象
     * @param <TCmd> 指令类型
     * @return 指令对象
     */
    static private <TCmd extends GeneratedMessageV3> TCmd cast(Object msg) {
        if (!(msg instanceof GeneratedMessageV3)) {
            return null;
        } else {
            return (TCmd) msg;
        }
    }
}
