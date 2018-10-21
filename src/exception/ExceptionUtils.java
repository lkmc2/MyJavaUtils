package exception;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.time.DateTimeException;

/**
 * @author lkmc2
 * @date 2018/10/21
 * @description 异常工具类
 */
public final class ExceptionUtils {

    public static void throwRuntimeException(Object... params) {
        of(RuntimeException.class, params);
    }

    public static void throwNullPointerException(Object... params) {
        of(NullPointerException.class, params);
    }

    public static void throwSecurityException(Object... params) {
        of(SecurityException.class, params);
    }

    public static void throwIllegalArgumentException(Object... params) {
        of(IllegalArgumentException.class, params);
    }

    public static void throwDateTimeException(Object... params) {
        of(DateTimeException.class, params);
    }

    public static void throwIndexOfBoundsException(Object... params) {
        of(IndexOutOfBoundsException.class, params);
    }

    public static void throwParseException(Object... params) throws ParseException {
        of(ParseException.class, params);
    }

    /**
     * 可以使用该方法抛出任意Exception子类的异常
     * @param clazz  Exception子类的Class类型
     * @param params 构造器参数
     * @param <T>    Exception子类
     * @throws T 将抛出的异常的类型
     */
    public static <T extends Exception> void of(Class<T> clazz, Object... params) throws T {
        throw reflectCreateException(clazz, params);
    }

    /**
     * 通过反射创建Exception子类的实例对象
     * @param clazz  Exception子类的Class类型
     * @param params 构造器参数
     * @param <T>    Exception子类
     * @return Exception子类的实例对象
     */
    public static <T extends Exception> T reflectCreateException(Class<T> clazz, Object... params) {
        // 获取参数的类型
        Class<?>[] paramsClasses = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramsClasses[i] = params[i].getClass();
        }

        try {
            // 获取Exception子类的构造器
            Constructor<T> constructor = clazz.getDeclaredConstructor(paramsClasses);

            if (constructor != null) {
                // 创建Exception子类实例对象
                return constructor.newInstance(params);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new RuntimeException(
                String.format("参数有误，无法创建[ %s ]类的对象，请参考[ %s ]类的几个构造方法填充参数",
                        clazz.getSimpleName(), clazz.getSimpleName())
        );
    }

}
