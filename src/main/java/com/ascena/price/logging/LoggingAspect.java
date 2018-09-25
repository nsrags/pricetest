package com.ascena.price.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ascena.price.common.constant.PriceConstants;


@Aspect
@Component
/**
 * Aspect for logging execution of service and Spring components.
 * @author SMeenavalli
 *
 */

public class LoggingAspect {
	
	
	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	@Autowired
	private Environment env;

	@Pointcut("within(com.ascena.price..*)")
	public void loggingPoincut() {
		/**
		 * logging Poincut
		 */
	}

	@AfterThrowing(pointcut = "loggingPoincut()", throwing = "ex")
	/**
	 * logAfterThrowing
	 * @param joinPoint JoinPoint
	 * @param ex Throwable
	 */
	public void logAfterThrowing(final JoinPoint joinPoint, final Throwable ex) {
		if (env.acceptsProfiles(PriceConstants.SPRING_PROFILE_DEV.toString(),
				PriceConstants.SPRING_PROFILE_LOCAL.toString())) {
			log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), ex.getCause(), ex);
		} else {
			log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), ex.getCause());
		}
	}

	@Around("loggingPoincut()")
	/**
	 * logAround
	 * @param joinPoint ProceedingJoinPoint
	 * @return Object
	 * @throws Throwable
	 */
	public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		}
		try {
			Object result = joinPoint.proceed();
			if (log.isDebugEnabled()) {
				log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), result);
			}
			return result;
		} catch (IllegalArgumentException ex) {
			log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

			throw ex;
		}
	}

}
