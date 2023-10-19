package com.ych.core.log.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@ConditionalOnProperty(name = "log.aspect.enabled", havingValue = "true", matchIfMissing = true)
public class LogAspect {

    @Pointcut("execution(* com.ych..*.controller.*Controller.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String args = new Gson().toJson(point.getArgs());
        MethodSignature signature = (MethodSignature) point.getSignature();
        String method = signature.getDeclaringType().getName() + signature.getName();
        long startTime = System.currentTimeMillis();
        Object response = point.proceed();
        long endTime = System.currentTimeMillis();
        String result = new Gson().toJson(response);
        log.info("**************************************** 日志开始 ****************************************");
        log.info("请求方法: {}", method);
        log.info("请求参数: {}", args);
        log.info("返回结果: {}", result);
        log.info("请求耗时: {} ms", endTime - startTime);
        log.info("**************************************** 日志结束 ****************************************");
        return response;
    }

}
