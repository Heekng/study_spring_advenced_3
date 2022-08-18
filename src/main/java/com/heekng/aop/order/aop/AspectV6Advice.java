package com.heekng.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {
    @Around("com.heekng.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    @Before("com.heekng.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinpoint) {
        log.info("test = {}", joinpoint.getThis());
        log.info("[before] {}", joinpoint.getSignature());
    }

    @AfterReturning(value = "com.heekng.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinpoint, Object result) {
        log.info("test = {}", joinpoint.getThis());
        log.info("[return] {} return={}", joinpoint.getSignature(), result);
    }

    @AfterThrowing(value = "com.heekng.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinpoint, Exception ex) {
        log.info("[ex] {} message={}", joinpoint.getSignature(), ex);
    }

    @After("com.heekng.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinpoint) {
        log.info("[after] {}", joinpoint.getSignature());
    }

}
