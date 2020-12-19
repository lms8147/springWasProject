package com.mysample.common.base;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BoardPointcut {
	@Pointcut("execution(* com.mysample.common.base.service.impl.*TT.*(..))")
	public void allPointcut() {}
}
