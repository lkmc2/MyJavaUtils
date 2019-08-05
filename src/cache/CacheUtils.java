package cache;
import java.util.concurrent.locks.Lock;
import	java.util.concurrent.locks.ReentrantReadWriteLock;
import	java.util.HashMap;
import java.util.Map;

/**
 * 缓存工具
 * @author lkmc2
 * @date 2019/8/5 12:38
 */
public final class CacheUtils {

    /** 缓存数据的 map **/
    private static Map<String, Object> map = new HashMap<>();

    /** 读写锁 **/
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /** 读锁 **/
    private static Lock readLock = readWriteLock.readLock();

    /** 写锁 **/
    private static Lock writeLock = readWriteLock.writeLock();

    /**
     * 根据 key 获取缓存数据
     * @param key 关键字
     * @return 对应的数据
     */
    public static Object get(String key) {
        readLock.lock();

        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 设置 key 对应的数据，并返回旧的数据
     * @param key 关键字
     * @param value 对应的数据
     * @return 旧的数据
     */
    public static Object put(String key, Object value) {
        writeLock.lock();

        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 清空所有缓存
     */
    public static void clear() {
        writeLock.lock();

        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

}
