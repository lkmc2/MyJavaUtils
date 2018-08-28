package singleton;

/**
 * @author lkmc2
 * @date 2018/8/19
 * @description 单例使用示例类
 */

// 打印机类
class Printer {
    private int times = 1;

    public void printMessage() {
        System.out.printf("正在进行第%d次打印……\n", times++);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打印完成");
    }
}

public class SingletonSample {

    public static void main(String[] args) {

        // 为Printer类创建单例对象
        Singleton<Printer> printerSingleton = new Singleton<Printer>() {
            @Override
            protected Printer create() {
                return new Printer();
            }
        };

        printerSingleton.get().printMessage();
        printerSingleton.get().printMessage();
        printerSingleton.get().printMessage();
        /*
         * 运行结果：
         * 正在进行第1次打印……
         * （3s后）
         * 打印完成
         * 正在进行第2次打印……
         * （3s后）
         * 打印完成
         * 正在进行第3次打印……
         * （3s后）
         * 打印完成
         */
    }
}
