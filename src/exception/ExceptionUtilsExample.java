package exception;

/**
 * @author lkmc2
 * @date 2018/10/21
 * @description 异常工具类使用例子
 */
public class ExceptionUtilsExample {

    public static void main(String[] args) {
        // ExceptionUtils提供了几个常用的异常作为方法，可根据该异常类的构造方法填入参数
        ExceptionUtils.throwRuntimeException();
        ExceptionUtils.throwDateTimeException("日期异常");
        ExceptionUtils.throwRuntimeException("空指针异常", new Throwable("错误信息"));
        ExceptionUtils.throwRuntimeException(new Throwable("错误信息"));

        // 如果需要ExceptionUtils中未提供的方法，可使用of(clazz, param...)方法，传入指定异常的类，及对应参数
        ExceptionUtils.of(NumberFormatException.class, "格式有误");
    }

}
