package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 单线程的线程池
 * @author lkmc2
 * @date 2019/9/18 17:10
 */
public final class SingleThreadPool {

    /** 单例模式 **/
    private static final class ThreadPool {
        /** 单线程的线程池 **/
        private static final ExecutorService INSTANCE = Executors.newSingleThreadExecutor();
    }

    /**
     * 获取线程池
     * @return 线程池
     */
    public static ExecutorService getInstance() {
        return ThreadPool.INSTANCE;
    }

    /**
     * 执行单个任务
     * @param task 可执行的任务
     */
    public static void execute(Runnable task) {
        getInstance().execute(task);
    }

    /**
     * 执行单个任务，有返回值
     * @param task 可执行的任务
     */
    public static Future<?> submit(Runnable task) {
        return getInstance().submit(task);
    }

    /**
     * 禁止提交新任务，并让主线程等待线程池中的所有任务执行完成
     */
    public static void shutdown() {
        getInstance().shutdown();
    }

}
