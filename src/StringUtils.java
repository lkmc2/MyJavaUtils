import java.util.ArrayList;
import java.util.List;

/**
 * Created by lkmc2 on 2018/5/28.
 * 字符串工具类
 */

public class StringUtils {

    /**
     * 将组数中的元素通过指定符号连接成字符串
     * @param separator 连接符
     * @param arr 数组
     * @return 字符串
     */
    public static String join(String separator, String... arr) {
        StringBuilder sb = new StringBuilder(50);
        for (int i = 0, len = arr.length; i < len; i++) {
            sb.append(arr[i]);
            if (i < len - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 将列表中的元素通过指定符号连接成字符串
     * @param separator 连接符
     * @param list 列表
     * @return 字符串
     */
    public static <T> String join(String separator, List<T> list) {
        StringBuilder sb = new StringBuilder(50);
        for (int i = 0, len = list.size(); i < len; i++) {
            sb.append(list.get(i));
            if (i < len - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {

        String[] words = {"Java", "Python", "Ruby", "Go" };

        String arrayResult = StringUtils.join("----", words);
        System.out.println(arrayResult);
        /*
        运行结果：Java----Python----Ruby----Go
         */

        List<String> list = new ArrayList<String>() {{ add("Andy"); add("Kim"); add("Jack"); add("Sam"); }};

        String listResult = StringUtils.join("****", list);
        System.out.println(listResult);
         /*
        运行结果：Andy****Kim****Jack****Sam
         */
    }
}
