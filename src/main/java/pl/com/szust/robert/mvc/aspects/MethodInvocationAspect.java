package pl.com.szust.robert.mvc.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodInvocationAspect {
  private static final Logger logger = Logger.getLogger(MethodInvocationAspect.class);
  
  @Around(value = "execution(* calculate(..))")
  public void controllerMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
    if ( logger.isDebugEnabled() ) {
      logger.debug("The " + joinPoint.getSignature().getName() + "method is about to be execute at " +DateTime.now());
    }
    joinPoint.proceed();
    if ( logger.isDebugEnabled() ) {
      logger.debug("The " + joinPoint.getSignature().getName() + "method is executed at " + DateTime.now());
    }
  }
}
