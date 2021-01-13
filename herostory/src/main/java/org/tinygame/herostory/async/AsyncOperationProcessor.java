package org.tinygame.herostory.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.MainThreadProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步操作处理器
 */
public final class AsyncOperationProcessor {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncOperationProcessor.class);

    /**
     * 单例对象
     */
    private static final AsyncOperationProcessor ASYNC_OPERATION_PROCESSOR = new AsyncOperationProcessor();

    /**
     * 创建线程数组
     */
    private final ExecutorService[] ASYNC_THREAD_ARRAY = new ExecutorService[8];


    /**
     * 私有化默认构造器
     */
    private AsyncOperationProcessor(){
        // 给每个线程初始化
        for (int i = 0; i < ASYNC_THREAD_ARRAY.length; i++) {
            final String threadName = "AsyncOperationProcessor" + i;
            ASYNC_THREAD_ARRAY[i] = Executors.newSingleThreadExecutor((newRunnable)->{
                Thread newThread = new Thread(newRunnable);
                newThread.setName(threadName);
                return newThread;
            });
        }
    }

    /**
     * 获取单例对象
     * @return 异步操作处理器对象
     */
    public static AsyncOperationProcessor getInstance(){
        return ASYNC_OPERATION_PROCESSOR;
    }

    /**
     * 线程执行
     * @param asyncOperation IAsyncOperation接口对象
     */
    public void process(IAsyncOperation asyncOperation){
        if (null == asyncOperation){
            return;
        }

        // 取出绑定id
        int bindId = asyncOperation.bindId();
        // 绑定id与线程数取模
        int index = bindId % ASYNC_THREAD_ARRAY.length;

        // 根据绑定id执行线程
        ASYNC_THREAD_ARRAY[index].submit(()->{
            LOGGER.info("打印当前线程："+Thread.currentThread().getName());
            try {
                // 完成登陆业务，并把用户实体放进回调函数里
                asyncOperation.doAsync();

                // 从回掉函数取出用户实体
                MainThreadProcessor.getInstance().process(asyncOperation::doFinish);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(),e);
            }
        });
    }


}
