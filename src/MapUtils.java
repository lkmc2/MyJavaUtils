import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by lkmc2 on 2018/4/25.
 * Map工具类
 */

public class MapUtils {
    // Map对象
    private static final Map<Object, Object> MAP;

    // 静态代码块中初始化Map
    static {
        MAP = new HashMap<>();
    }

    // 单例对象
    private static final class Holder {
        private static final MapUtils INSTANCE = new MapUtils();
    }

    public static MapUtils value(Object key, Object value) {
        MAP.put(key, value);
        return Holder.INSTANCE;
    }

    public static Map<Object, Object> toMap() {
        return MAP;
    }

    public static void main(String[] args) {
        Map<Object, Object> map = MapUtils.value(1, "Jack").value(2, "Andy").value(3, "Kim").toMap();
        System.out.println(map);
    }
}
