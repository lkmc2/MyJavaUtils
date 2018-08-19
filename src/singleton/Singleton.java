package singleton;

/**
 * @author lkmc2
 * @date 2018/8/19
 * @description 单例抽象类
 * @see singleton.SingletonSample
 */

public abstract class Singleton<T> {
    // 对象实例
    private T mInstance;

    public Singleton() {
    }

    /**
     * 创建对象的方法
     * @return 创建后的对象
     */
    protected abstract T create();

    /**
     * 获取对象实例
     * @return 对象实例
     */
    public final T get() {
        // 加锁，防止多线程争抢资源
        synchronized (this) {
            // 当实例对象为空
            if (mInstance == null) {
                // 调用create创建实例对象
                mInstance = create();
            }
            return mInstance;
        }
    }
}
