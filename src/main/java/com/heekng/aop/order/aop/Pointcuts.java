package com.heekng.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
public class Pointcuts {

    // com.heekng.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* com.heekng.aop.order..*(..))")
    public void allOrder() {} // pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}

}
