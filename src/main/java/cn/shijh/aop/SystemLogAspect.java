package cn.shijh.aop;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class SystemLogAspect {
    private final Logger logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());

    @Pointcut("execution(* cn.shijh.service.*.*(..))")
    private void service() {
    }

    @Before(value = "service()")
    private void serviceInfo(JoinPoint joinpoint) {
        log.info(Arrays.toString(joinpoint.getArgs()));
    }

    @AfterThrowing(throwing = "e", pointcut = "SystemLogAspect.service()")
    private void exceptionLog(JoinPoint jp, Exception e) {
        log.error(jp.getTarget() + String.format(": '%s'", e.getMessage()));
    }

}
