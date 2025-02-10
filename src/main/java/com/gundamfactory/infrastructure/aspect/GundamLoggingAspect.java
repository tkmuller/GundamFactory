package com.gundamfactory.infrastructure.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class GundamLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(GundamLoggingAspect.class);

    @Pointcut("execution(* com.gundamfactory.infrastructure.adapters.GundamRepositoryAdapter.findById(..))")
    public void getGundamByIdMethod() {
    }

    @Before("getGundamByIdMethod() && args(id)")
    public void logIfNegativeId(JoinPoint joinPoint, Long id) {
        if (id < 0) {
            logger.warn("Se intentó acceder a un Gundam con un ID negativo: {}", id);
        }
    }

    @AfterReturning(pointcut = "getGundamByIdMethod()", returning = "result")
    public void logIfGundamNotFound(JoinPoint joinPoint, Optional<?> result) {
        if (result.isEmpty()) {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];
            logger.warn("No se encontró ningún Gundam con el ID: {}", id);
        }
    }

}
