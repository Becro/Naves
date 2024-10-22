package com.adrian.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class NaveIdNegativoAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(NaveIdNegativoAspect.class);

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping) && args(id) && args(id,..) && id instanceof Long && id < 0")
    public void logIdNegativo(JoinPoint joinPoint, Long id) {
        LOGGER.warn("Requesting nave with negative ID: {}", id);
    }
}
