package com.iflytek.bbs.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component  // Spring注解
@Aspect
public class LogAspect {

	// 增强处理：在目标方法执行之前植入增强
	@Before("execution(* com.iflytek.bbs.service.impl.*.*(..))")
	public void beforeDo() {
		System.out.println("------------>Before");
	}

	// 增强处理：在目标方法结束后植入增强（不管成功或异常终止）
	@After("execution(* com.iflytek.bbs.service.impl.*.*(..))")
	public void afterDo() {
		System.out.println("------------>After");
	}

	// 增强处理：在目标方法正常完成后植入增强
	// result：指定形参名，用于访问目标方法的返回值
	@AfterReturning(returning = "result", pointcut = "execution(* com.iflytek.bbs.service.impl.*.*(..))")
	public void afterReturningDo(Object result) {
		System.out.println("------------>AfterReturning，返回值：" + result);
	}

	// 增强处理：主要用于处理程序中未处理的异常
	// ex：指定形参名，用于访问目标方法抛出的异常
	@AfterThrowing(throwing = "ex", pointcut = "execution(* com.iflytek.bbs.service.impl.*.*(..))")
	public void afterThrowingDo(Throwable ex) {
		System.out.println("------------>AfterThrowing，异常：" + ex);
	}

	// 增强处理：Before和AfterReturning的总和
	@Around("execution(* com.iflytek.bbs.service.impl.*.*(..))")
	public Object aroundDo(ProceedingJoinPoint jp) throws Throwable {
		// 获取方法执行的参数
		Object[] args = jp.getArgs();
		// 执行目标方法
		Object result = jp.proceed(args);
		System.out.println("----->目标方法返回值" + result);
		return result;
	}
}
