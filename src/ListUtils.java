import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/10/25
 * @description List工具类
 */
public class ListUtils {

    /**
     * 快速生成具有指定值的List
     * @param params 列表中的值
     * @param <T> 值的类型
     * @return 带有指定参数值的ArrayList
     */
    @SafeVarargs
    public static <T> List<T> of(T... params) {
        return new ArrayList<T>(Arrays.asList(params));
    }

    public static void main(String[] args) {
        List<Integer> integerList = ListUtils.of(1, 3, 5, 7, 9);
        System.out.println(integerList);
        // 运行结果：[1, 3, 5, 7, 9]

        List<String> stringList = ListUtils.of("jack", "andy", "wang");
        System.out.println(stringList);
        //  运行结果：[jack, andy, wang]
    }

}
