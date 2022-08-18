package com.heekng.aop;

import com.heekng.aop.order.aop.AspectV1;
import com.heekng.aop.order.aop.AspectV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import(AspectV1.class)
@Import(AspectV2.class)
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

}
