package exception;

/**
 * @author lkmc2
 * @date 2018/10/21
 * @description 异常工具类使用例子
 */
public class ExceptionUtilsExample {

    public static void main(String[] args) {
        // ExceptionUtils提供了几个常用的异常作为方法，用于抛出指定异常，可根据该异常类的构造方法填入参数
        ExceptionUtils.throwRuntimeException();
        ExceptionUtils.throwDateTimeException("日期异常");
        ExceptionUtils.throwRuntimeException("空指针异常", new Throwable("错误信息"));
        ExceptionUtils.throwRuntimeException(new Throwable("错误信息"));

        // 如果需要ExceptionUtils中未提供的方法，可使用of(clazz, param...)方法，传入指定异常的类，及对应参数
        ExceptionUtils.throwOf(NumberFormatException.class, "格式有误");

        // 当此对象为null时，以下方法会抛出异常
        Object obj = null;

        // ExceptionUtils也提供了当传入的第一个参数为null时，抛出指定异常的方法
        ExceptionUtils.throwRuntimeExceptionIfNull(obj, "对象必须有值");
        ExceptionUtils.throwNullPointerExceptionIfNull(obj, "参数不能为null");

        // 若ExceptionUtils未提供想要的方法，可以使用ifNullThrow(clazz, obj, param...)方法进行指定想要抛出的异常
        ExceptionUtils.ifNullThrow(NumberFormatException.class, obj,"格式有误");
    }

}
