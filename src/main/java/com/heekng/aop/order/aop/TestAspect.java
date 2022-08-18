package com.heekng.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class TestAspect {

    @Around("com.heekng.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("joinPoint.getThis() = {}", joinPoint.getThis());
        log.info("joinPoint.getThis().getClass() = {}", joinPoint.getThis().getClass());
        log.info("joinPoint.getTarget() = {}", joinPoint.getTarget());
        log.info("joinPoint.getTarget().getClass() = {}", joinPoint.getTarget().getClass());
        return joinPoint.proceed();
    }
}
