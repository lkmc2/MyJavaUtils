/**
 * @author lkmc2
 * @date 2018/10/25
 * @description 打印输出
 */
public class Print {

    public static void print(String msg) {
        System.out.print(msg);
    }

    public static void println() {
        System.out.println();
    }

    public static void println(String msg) {
        System.out.println(msg);
    }

    public static void printf(String msg, Object... params) {
        System.out.printf(msg, params);
    }

    public static void main(String[] args) {
        print("never");
        println();

        println("show");

        printf("this is %d", 3);

        /*
            运行结果：
                never
                show
                this is 3
         */
    }

}
