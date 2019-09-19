package thread;

/**
 * 全局锁和单线程线程池的测试
 * @author lkmc2
 * @date 2019/9/19 11:00
 */
public class GlobalLockAndSingleThreadPoolTest {

    public static void main(String[] args) {
        SingleThreadPool.execute(() -> {
            // 等待锁释放
            while (GlobalLock.isLock()) {
            }

            try {
                // 加锁
                GlobalLock.lock("任务1");
                show("任务1");
            } finally {
                // 解锁
                GlobalLock.unlock();
                System.out.println("任务1" + "锁释放");
            }
        });

        SingleThreadPool.execute(() -> {
            while (GlobalLock.isLock()) {
            }

            try {
                GlobalLock.lock("任务2");
                show("任务2");
            } finally {
                GlobalLock.unlock();
                System.out.println("任务2" + "锁释放");
            }
        });

        SingleThreadPool.execute(() -> {
            while (GlobalLock.isLock()) {
            }

            try {
                GlobalLock.lock("任务3");
                show("任务3");
            } finally {
                GlobalLock.unlock();
                System.out.println("任务3" + "锁释放");
            }
        });

        // 停止提交新任务，并等待已提交的任务执行完成
        SingleThreadPool.shutdown();
    }

    public static void show(String info) {
        try {
            Thread.sleep(3000);
            System.out.println(info + "执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
