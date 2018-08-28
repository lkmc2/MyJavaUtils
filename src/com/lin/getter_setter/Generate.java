package com.lin.getter_setter;

import java.lang.annotation.*;

/**
 * @author lkmc2
 * @date 2018/8/21
 * @description 要生成setter和getter的注解
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Generate {
    boolean createGetter() default true;
    boolean createSetter() default true;
    boolean createDefaultConstructor() default true;
    boolean createParamsConstructor() default true;
}
