package com.skynet.aspect;

import com.alibaba.fastjson.JSON;
import com.skynet.annotation.SysLogAnno;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Skynet
 * @date 2017年05月04日 15:58
 */
@Aspect
@Component
public class SysLogAspect {

    private Logger logger = LogManager.getLogger(SysLogAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 使用execution配置日志切入点：作用匹配的类方法上
     */
    @Pointcut("execution(* com.skynet.web.HelloController.*(..))")
    public void logPointCut() {
    }


    /**
     * 通过annotation注释的方式设置切入点：将作用所有带SysLogAnno注释的方法上
     */
    @Pointcut("@annotation(com.skynet.annotation.SysLogAnno)")
    public void logPointCut2() {
    }


    @Before("logPointCut()")
    public void doBefore(JoinPoint joinpoint) {
        //记录开始时间
        startTime.set(System.currentTimeMillis());

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //记录URL、HTTP_METHOD、IP、CLASS_METHOD、ARGS
        logger.info("URL：{}", request.getRequestURL().toString());
        logger.info("HTTP_METHOD：{}", request.getMethod());
        logger.info("IP：{}", request.getRemoteAddr());
        //获取类名的两个方式：
        //  1、joinpoint.getSignature().getDeclaringTypeName()
        //  2、joinpoint.getTarget().getClass().getName()
        logger.info("CLASS_METHOD：{}", joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName() + "()");
        logger.info("CLASS_METHOD_2：{}", joinpoint.getTarget().getClass().getName() + "." + joinpoint.getSignature().getName() + "()");
        logger.info("ARGS：{}", JSON.toJSONString(joinpoint.getArgs()));
    }


    @AfterReturning(pointcut = "logPointCut2()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.error("Annotation {}", signature.getMethod().getAnnotation(SysLogAnno.class).value());
        logger.info("RESPONSE {}", result);
        logger.info("The Method {} Use Time {}", joinPoint.getSignature().getName(), System.currentTimeMillis() - startTime.get());
    }


    private String getArgName(Class clazz, String method) throws NoSuchMethodException {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method1 : methods) {
            System.out.println(method1);
        }
        return "";
    }

}
