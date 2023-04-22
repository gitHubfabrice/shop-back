package com.fatechnologies.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * @author Christian Amani
 */
@Aspect
public class LoggingAspect {

  private static final String SPRING_PROFILE_DEV_DEVELOPMENT = "dev";
  private static final String SPRING_PROFILE_PROD_DEVELOPMENT = "prod";

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final Environment environment;

  public LoggingAspect(Environment environment) {
    this.environment = environment;
  }

  @AfterThrowing
  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    if (environment.acceptsProfiles(Profiles.of(SPRING_PROFILE_DEV_DEVELOPMENT))) {
      logger.error("Exception in {}.{}() with cause = '{}' and exception = '{}'",
          joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL",
          e.getMessage(), e);
      logger.info("Dev environment !");
    } else if (environment.acceptsProfiles(Profiles.of(SPRING_PROFILE_PROD_DEVELOPMENT))) {
      logger.error("Exception in {}.{}() with cause = {}",
          joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
      logger.info("Prod environment !");
    }
  }

  @Around("within(@org.springframework.stereotype.Repository *)" +
      " || within(@org.springframework.stereotype.Component *)" +
      " || within(@org.springframework.stereotype.Service *)" +
      " || within(@org.springframework.web.bind.annotation.RestController *)")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    if (logger.isDebugEnabled()) {
      logger.debug("Enter: {}.{}() with argument[s] = {}",
          joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
    try {
      Object result = joinPoint.proceed();
      if (logger.isDebugEnabled()) {
        logger.debug("Exit: {}.{}() with result = {}",
            joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), result);
      }
      return result;
    } catch (IllegalArgumentException e) {
      logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
          joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

      throw e;
    }
  }

  @Around("@annotation(com.fatechnologies.config.LogExecutionTime)")
  public Object logTimeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

    // Get intercepted method details
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();

    // Measure method execution time
    StopWatch stopWatch = new StopWatch(className + "->" + methodName);
    stopWatch.start(methodName);
    Object result = joinPoint.proceed();
    stopWatch.stop();

    // Log method execution time
    if (logger.isInfoEnabled()) {
      logger.info(stopWatch.prettyPrint());
    }

    return result;
  }
}
