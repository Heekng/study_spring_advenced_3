package com.heekng.aop.member;

import com.heekng.aop.member.annotation.MethodAop;
import com.heekng.aop.member.annotation.ClassAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
