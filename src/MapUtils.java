import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lkmc2 on 2018/4/25.
 * Map工具类
 */

public class MapUtils {
    // Map对象
    private static final Map<Object, Object> MAP;

    // 静态代码块中初始化Map
    static {
        MAP = new HashMap<>(10);
    }

    // 单例对象
    private static final class Holder {
        private static final MapUtils INSTANCE = new MapUtils();
    }

    /**
     * 为map添加键值对
     * @param key 键
     * @param value 值
     * @return MapUtils对象
     */
    public static MapUtils value(Object key, Object value) {
        MAP.put(key, value);
        return Holder.INSTANCE;
    }

    /**
     * 删除map中的键值对
     * @param key 键
     * @return MapUtils对象
     */
    public static MapUtils remove(Object key) {
        MAP.remove(key);
        return Holder.INSTANCE;
    }

    /**
     * 将map的内容返回
     * @return map对象
     */
    public static Map<Object, Object> toMap() {
        return MAP;
    }

    /**
     * 将map的内容返回，并清空map的内容
     * @return map对象
     */
    public static Map<Object, Object> toMapClear() {
        Map<Object, Object> resultMap = new HashMap<>(MAP);
        MAP.clear();
        return resultMap;
    }

    public static void main(String[] args) {
        // 创建map对象并同时存入多个值（使用toMap方法将保留之前存入的值）
        Map<Object, Object> map1 = MapUtils.value(1, "Jack").value(2, "Andy").value(3, "Kim").toMap();
        System.out.println(map1);
        /*
        运行结果：{1=Jack, 2=Andy, 3=Kim}
         */

        // 创建map对象并同时存入多个值（使用toMap方法返回map后，将清除所有存入的值）
        Map<Object, Object> map2 = MapUtils.value(4, "Java").value(5, "Python").value(6, "Ruby").toMapClear();
        System.out.println(map2);
        /*
        运行结果：{1=Jack, 2=Andy, 3=Kim, 4=Java, 5=Python, 6=Ruby}
         */

        // 创建map对象并同时存入多个值（此时产生的map不包含之前的值）
        Map<Object, Object> map3 = MapUtils.value("Age", 27).value("Time", new Date()).value("Float", 32F).toMap();
        System.out.println(map3);
         /*
        运行结果：{Float=32.0, Time=Mon May 28 01:03:08 CST 2018, Age=27}
         */
    }
}
