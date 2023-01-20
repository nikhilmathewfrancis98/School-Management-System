package com.kenschool.Config.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@Aspect
public class LoggerAspect {
    // First aspect method for all the point cuts under our main-package
    @Around("execution(* com.kenschool..*.*(..))")
    public Object Log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info(proceedingJoinPoint.getSignature().toString() + " Method execution starts");
        Instant start = Instant.now();
        Object res9ult = proceedingJoinPoint.proceed();
        Instant stop = Instant.now();
        long timeElapsed = Duration.between(start, stop).toMillis();
        log.info("Time took to execute " + proceedingJoinPoint.getSignature().toString() + " method is : " + timeElapsed);
        log.info(proceedingJoinPoint.getSignature().toString() + " method execution end");
        return res9ult;
    }

    @AfterThrowing(value = "execution(* com.kenschool..*.*(..))", throwing = "ex")
    public void ExceptionLogger(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature() + " An exception happened due to : " + ex.getMessage());
    }
}
