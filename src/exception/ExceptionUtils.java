package exception;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.DateTimeException;

/**
 * @author lkmc2
 * @date 2018/10/21
 * @description 异常工具类
 */
public final class ExceptionUtils {

    public static void throwRuntimeExceptionIfNull(Object obj, Object... params) {
        ifNullThrow(RuntimeException.class, obj, params);
    }

    public static void throwNullPointerExceptionIfNull(Object obj, Object... params) {
        ifNullThrow(NullPointerException.class, obj, params);
    }

    public static void throwSecurityExceptionIfNull(Object obj, Object... params) {
        throwOf(SecurityException.class, obj, params);
    }

    public static void throwIllegalArgumentExceptionIfNull(Object obj, Object... params) {
        ifNullThrow(IllegalArgumentException.class, obj, params);
    }

    public static void throwDateTimeExceptionIfNull(Object obj, Object... params) {
        throwOf(DateTimeException.class, obj, params);
    }

    public static void throwIndexOfBoundsExceptionIfNull(Object obj, Object... params) {
        ifNullThrow(IndexOutOfBoundsException.class, obj, params);
    }

    /**
     * 当参数obj等于null时，抛出指定的异常
     * @param clazz  Exception子类的Class类型
     * @param obj 需要进行判空的对象
     * @param params 构造器参数
     * @param <T>    Exception子类
     * @throws T 将抛出的异常的类型
     */
    public static <T extends Exception> void ifNullThrow(Class<T> clazz, Object obj, Object... params) throws T {
        if (obj == null) {
            throw reflectCreateException(clazz, params);
        }
    }

    public static void throwRuntimeException(Object... params) {
        throwOf(RuntimeException.class, params);
    }

    public static void throwNullPointerException(Object... params) {
        throwOf(NullPointerException.class, params);
    }

    public static void throwSecurityException(Object... params) {
        throwOf(SecurityException.class, params);
    }

    public static void throwIllegalArgumentException(Object... params) {
        throwOf(IllegalArgumentException.class, params);
    }

    public static void throwDateTimeException(Object... params) {
        throwOf(DateTimeException.class, params);
    }

    public static void throwIndexOfBoundsException(Object... params) {
        throwOf(IndexOutOfBoundsException.class, params);
    }

    /**
     * 可以使用该方法抛出任意Exception子类的异常
     * @param clazz  Exception子类的Class类型
     * @param params 构造器参数
     * @param <T>    Exception子类
     * @throws T 将抛出的异常的类型
     */
    public static <T extends Exception> void throwOf(Class<T> clazz, Object... params) throws T {
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
