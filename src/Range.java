import java.util.Arrays;

/**
 * @author lkmc2
 * @date 2018/10/25
 * @description 范围
 */
public class Range {

    /**
     * 生成值从[0, count)范围的数组
     * @param count 生成值的个数
     * @return 值从[0, count)范围的数组
     */
    public static int[] range(int count) {
        int[] intArray = new int[count];

        for (int i = 0; i < count; i++) {
            intArray[i] = i;
        }
        return intArray;
    }

    /**
     * 生成值从[start, end)范围的数组
     * @param start 起始值
     * @param end 结束值
     * @return 值从[start, end)范围的数组
     */
    public static int[] range(int start, int end) {
        int count = end - start;
        int[] intArray = new int[count];

        for (int i = 0; i < count; i++) {
            intArray[i] = i + start;
        }
        return intArray;
    }

    /**
     * 生成值从[start, end)范围的数组，每个值之间步长相隔step
     * @param start 起始值
     * @param end 结束值
     * @param step 步长
     * @return 值从[start, end)范围的数组
     */
    public static int[] range(int start, int end, int step) {
        int count = (end - start) / step;
        int[] intArray = new int[count];

        for (int i = 0; i < count; i++) {
            intArray[i] = start + (i * step);
        }
        return intArray;
    }


    public static void main(String[] args) {
        int[] range1 = range(4);
        System.out.println(Arrays.toString(range1));
        // 运行结果：[0, 1, 2, 3]

        int[] range2 = range(3, 7);
        System.out.println(Arrays.toString(range2));
        // 运行结果：[3, 4, 5, 6]

        int[] range3 = range(2, 10, 2);
        System.out.println(Arrays.toString(range3));
        // 运行结果：[2, 4, 6, 8]

        for (int value : range(2, 11, 2)) {
            System.out.println("value = " + value);
        }
        /*
            运行结果：
                value = 2
                value = 4
                value = 6
                value = 8
         */
    }

}
