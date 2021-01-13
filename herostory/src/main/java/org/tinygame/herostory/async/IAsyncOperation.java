package org.tinygame.herostory.async;

/**
 * 异步操作的接口类
 */
public interface IAsyncOperation {

    /**
     * 用户跟线程绑定的id,用户只在一个线程内登陆
     * @return id
     */
    default int bindId(){
        return 0;
    }

    /**
     * 异步操作逻辑
     */
    void doAsync();

    /**
     * default修饰可以不用必须实现
     * 完成操作逻辑
     */
    default void doFinish(){

    }
}
