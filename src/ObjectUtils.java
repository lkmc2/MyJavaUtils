import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by lkmc2 on 2018/4/25.
 * 对象工具类
 */

public class ObjectUtils {

    /**
     * 限定对象必须非空，非空时返回原对象，否则抛出异常
     *
     * @param obj 对象
     * @param <T> 对象类型
     * @return 非法参数异常
     */
    public static <T> T mustNotNull(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("对象必须非空");
        }
        return obj;
    }

    @Test
    public void testMustNotNull() {
        ArrayList arrayList = new ArrayList();

        // 测试通过
        assertEquals(arrayList, ObjectUtils.mustNotNull(arrayList));
    }
}
