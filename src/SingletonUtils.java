import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lkmc2
 * @date 2018/8/19
 * @description 单例工具类
 */

public class SingletonUtils {
    /** 存储类的类型和对应的实例对象 **/
    private static final ConcurrentMap<Class, Object> MAP = new ConcurrentHashMap<>();

    /**
     * 获取指定类型的单例对象
     * @param type 类的Class类型
     * @param <T> 类的泛型
     * @return 指定类型的单例对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getSingleton(Class<T> type) {
        // 从MAP中获取类型对应的实例对象
        Object obj = MAP.get(type);

        try {
            // 对象为空
            if (obj == null) {
                synchronized (MAP) {
                    // 使用该类的无参构造器生成实例对象
                    obj = type.newInstance();
                    // 将实例对象放入MAP中
                    MAP.put(type, obj);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    /**
     * 移除指定类型的单例对象
     * @param type 类的Class类型
     * @param <T> 类的泛型
     */
    public static <T> void remove(Class<T> type) {
        MAP.remove(type);
    }

    public static void main(String[] args) {
        SingletonUtils.getSingleton(Monitor.class).displayMessage();
        SingletonUtils.getSingleton(Monitor.class).displayMessage();
        SingletonUtils.getSingleton(Monitor.class).displayMessage();
        /*
            运行结果：
            第1次打开显示器……
            （3s后）
            打开成功
            第2次打开显示器……
            （3s后）
            打开成功
            第3次打开显示器……
            （3s后）
            打开成功
         */
    }
}

// 显示器
class Monitor {
    private int count = 1;

    public void displayMessage() {
        System.out.printf("第%d次打开显示器……\n", count++);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打开成功");
    }
}