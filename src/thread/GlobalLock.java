package thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 全局锁
 * @author lkmc2
 * @date 2019/9/18 15:37
 */
public final class GlobalLock {

    /** 锁信息（不可变类） **/
    private static final class LockInfo {
        /** 锁名称 **/
        private final String lockName;

        /** 锁状态 **/
        private final boolean lockStatus;

        private LockInfo(String lockName, boolean lockStatus) {
            this.lockName = lockName;
            this.lockStatus = lockStatus;
        }

        private boolean getLockStatus() {
            return lockStatus;
        }

        private String getLockName() {
            return lockName;
        }
    }

    /** 默认锁名称 **/
    private static final String DEFAULT_LOCK_NAME = "后台任务";
    
    /** 默认锁信息 **/
    private static final LockInfo DEFAULT_LOCK_INFO = new LockInfo(DEFAULT_LOCK_NAME, false);
    
    /** 锁信息（该变量可保证操作都是原子操作，无需加锁） **/
    private static AtomicReference<LockInfo> lockInfo = new AtomicReference<>(DEFAULT_LOCK_INFO);

    /**
     * 加锁
     */
    public static void lock() {
        lock(DEFAULT_LOCK_NAME);
    }

    /**
     * 加锁
     * @param lockName 锁名称
     */
    public static void lock(String lockName) {
        while (true) {
            LockInfo oldLockInfo = GlobalLock.lockInfo.get();
            LockInfo newLockInfo = new LockInfo(lockName, true);
            if (lockInfo.compareAndSet(oldLockInfo, newLockInfo)) {
                return;
            }
        }
    }

    /**
     * 解锁
     */
    public static void unlock() {
        while (true) {
            LockInfo oldLockInfo = GlobalLock.lockInfo.get();
            if (lockInfo.compareAndSet(oldLockInfo, DEFAULT_LOCK_INFO)) {
                return;
            }
        }
    }

    /**
     * 判断是否上锁
     * @return 是否上锁
     */
    public static boolean isLock() {
        return lockInfo.get().getLockStatus();
    }

    /**
     * 获取锁名称
     * @return 锁名称
     */
    public static String getLockName() {
        return lockInfo.get().getLockName();
    }

}
