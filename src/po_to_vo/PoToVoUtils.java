package po_to_vo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author lkmc2
 * @date 2018/8/21
 * @description 将Plain Object转换成View Object类
 */

public class PoToVoUtils {

    public static <F, T> T convert(F fromObj, Class<T> toClazz) {
        T resultObj = null;

        try {
            resultObj = setParamsToObj(fromObj, toClazz);
        } catch (NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return resultObj;
    }

    @SuppressWarnings("unchecked")
    private static <F, T> T setParamsToObj(F fromObj, Class<T> toClazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<F> fromClazz = (Class<F>) fromObj.getClass();

        T resultObj = toClazz.getDeclaredConstructor().newInstance();

        Field[] fields = toClazz.getDeclaredFields();
        for (Field field : fields) {
            Method getterMethod = obtainGetterMethodInObj(fromClazz, field);
            Method setterMethod = obtainSetterMethodInObj(toClazz, field);

            Object value = getterMethod.invoke(fromObj, null);
            setterMethod.invoke(resultObj, value);
        }

        return resultObj;
    }

    private static Method obtainGetterMethodInObj(Class<?> clazz,  Field field) {
        return requireGetterOSetterMethod(clazz, field, true);
    }

    private static Method obtainSetterMethodInObj(Class<?> clazz,  Field field) {
        return requireGetterOSetterMethod(clazz, field, false);
    }

    private static Method requireGetterOSetterMethod(Class<?> clazz, Field field, boolean isGetterMethod) {
        Method method;
        String methodName;
        String fieldName = field.getName();

        if (isGetterMethod) {
            methodName = toGetterMethodName(fieldName);
            method = getMethodByName(clazz, methodName, null);
        } else {
            methodName = toSetterMethodName(fieldName);
            method = getMethodByName(clazz, methodName, field.getType());
        }

        return method;
    }

    private static Method getMethodByName(Class<?> clazz, String methodName, Class<?> methodParamType) {
        Method method;

        try {
            if (methodParamType == null) {
                method = clazz.getDeclaredMethod(methodName);
            } else {
                method = clazz.getDeclaredMethod(methodName, methodParamType);
            }
        } catch (NoSuchMethodException | SecurityException e) {
            String clazzName = clazz.getSimpleName();
            throw new RuntimeException(clazzName + "类缺失" + methodName + "( )方法");
        }
        return method;
    }

    private static String toSetterMethodName(String fieldName) {
        return "set" + toTitle(fieldName);
    }

    private static String toGetterMethodName(String fieldName) {
        return "get" + toTitle(fieldName);
    }

    private static String toTitle(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }


    public static void main(String[] args) {
        Product product = new Product(1, "Jack", 13, new Date(), 302);
        ProductVo productVo = PoToVoUtils.convert(product, ProductVo.class);

        System.out.println("product = " + product);
        System.out.println("productVo = " + productVo);
    }

}



