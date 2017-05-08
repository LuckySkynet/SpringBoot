package com.skynet.annotation;

import java.lang.annotation.*;

/**
 * 注解简介：
 * Target:表示注释作用的类型，例如类或者方法
 * Retention:表示注释的生命周期，设置为RUNTIME则可使该注释被JVM保留，以便在运行时能通过反射获取该注释
 * Documented:默认注释是不被javadoc工具进行记录的，设置Documented后则可使注释被添加到javadoc中
 *
 * @author Skynet
 * @date 2017年05月04日 17:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnno {

    String value() default "";

}
