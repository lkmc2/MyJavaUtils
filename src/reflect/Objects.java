package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/8/8
 * @description 对象工具类
 */
public class Objects {

    /**
     * 调用对象的方法
     * @param object 对象
     * @param methodName 方法名
     * @param params 方法参数
     * @return 执行方法后的返回值
     */
    public static Object callMethod(Object object, String methodName, Object... params) {
        Class<?>[] paramsClasses = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramsClasses[i] = params[i].getClass();
        }

        Method method;
        Object result = null;
        Class<?> clazz = object.getClass();

        try {
            method = clazz.getMethod(methodName, paramsClasses);
            result = method.invoke(object, params);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据传入的对象类型和参数调用对应构造器生成对象
     * @param clazz 对象类
     * @param params 构造器参数
     * @param <T> 对象类型
     * @return 使用对应构造器创建的新对象
     */
    public static <T> T newObj(Class<T> clazz, Object... params) {
        Class<?>[] paramsClasses = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramsClasses[i] = params[i].getClass();
        }

        Constructor<T> constructor;
        try {
            constructor = clazz.getDeclaredConstructor(paramsClasses);

            if (constructor != null) {
                return constructor.newInstance(params);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new RuntimeException(String.format("无法创建【%s】类的对象", clazz.getName()));
    }

    public static void main(String[] args) {
        Person person = newObj(Person.class, "Jack", 18, "Swimming");
        System.out.println(person);
        /*
         * 运行结果：Person{name='Jack', age=18, hobby='Swimming'}
         */

        Object result = callMethod(person, "getHobby");
        System.out.println(result);
        /*
         * 运行结果：Swimming
         */

        result = callMethod(person, "setAge", 33);
        System.out.println(person);
        /*
         * 运行结果：Person{name='Jack', age=33, hobby='Swimming'}
         */
    }

}
