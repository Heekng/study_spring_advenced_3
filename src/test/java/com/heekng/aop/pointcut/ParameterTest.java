package com.heekng.aop.pointcut;

import com.heekng.aop.member.MemberService;
import com.heekng.aop.member.annotation.ClassAop;
import com.heekng.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* com.heekng.aop.member..*.*(..))")
        private void allMember() {
        }

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs1]{}, arg={}", joinPoint.getSignature(), arg1);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg, ..)")
        public Object logArgs1(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2]{}, arg={}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg,..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] arg={}", arg);
        }

        @Before("allMember() && this(obj)")
        public void thisArgs(JoinPoint joinpoint, MemberService obj) {
            log.info("[this]{} obj={}", joinpoint.getSignature(), obj.getClass());
        }

        @Before("allMember() && target(obj)")
        public void targetArgs(JoinPoint joinpoint, MemberService obj) {
            log.info("[target]{} obj={}", joinpoint.getSignature(), obj.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void atTargetArgs(JoinPoint joinpoint, ClassAop annotation) {
            log.info("[@target]{} obj={}", joinpoint.getSignature(), annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void atWithinArgs(JoinPoint joinpoint, ClassAop annotation) {
            log.info("[@within]{} obj={}", joinpoint.getSignature(), annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void atWithinArgs(JoinPoint joinpoint, MethodAop annotation) {
            log.info("[@annotation]{} annotationValue={}", joinpoint.getSignature(), annotation.value());
        }

    }
}