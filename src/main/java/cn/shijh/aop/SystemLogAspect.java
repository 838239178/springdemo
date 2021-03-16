package cn.shijh.aop;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemLogAspect {
    private final Logger logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());

    @Pointcut("execution(* cn.shijh.service.*.*(..))")
    private void service() {
    }

    @AfterThrowing(throwing = "e", pointcut = "SystemLogAspect.service()")
    private void exceptionLog(JoinPoint jp, Exception e) {
        logger.error(jp.getTarget() + String.format(": '%s'", e.getMessage()));
    }

}
