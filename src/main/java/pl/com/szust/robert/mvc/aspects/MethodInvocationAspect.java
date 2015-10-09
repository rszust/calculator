package pl.com.szust.robert.mvc.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import pl.com.szust.robert.mvc.model.CalculateRequest;
import pl.com.szust.robert.mvc.model.CalculateResponse;

@Aspect
@Component
public class MethodInvocationAspect {
  private static final Logger logger = Logger.getLogger(MethodInvocationAspect.class);
  
  @Pointcut("pl.com.szust.robert.mvc.aspects.BeanPointcut.isPublic() && pl.com.szust.robert.mvc.aspects.SystemArchitecture.inControllerLayer()"
  		+ " && args(request,..)")
  public void hasCalculateRequestAttribute(CalculateRequest request) {
	  
  }
  
  @Before(value = "hasCalculateRequestAttribute(request)")
  public void logCalculateRequest(CalculateRequest request) {
	  logger.info("Calculate request is " + request.getExpression());
  }
  
  @AfterReturning(value = " within(pl.com.szust.robert.mvc.controllers.*) && execution( pl.com.szust.robert.mvc.model.CalculateResponse calculate(..)) ",returning = "response") 
  public void logCalculateResponse(CalculateResponse response) {
	 logger.info("Calculated response is " + response.getResponse()); 
  }
  
  @AfterThrowing(value = "@within(org.springframework.stereotype.Controller) && execution( pl.com.szust.robert.mvc.model.CalculateResponse calculate(..) )", throwing = "exception")
  public void logException(Exception exception) {
	  logger.info("Exception happened during processign expression: " + exception.getMessage());	  
  }
  
  
  
  @Around(value = "pl.com.szust.robert.mvc.aspects.BeanPointcut.isPublic() && pl.com.szust.robert.mvc.aspects.SystemArchitecture.inControllerLayer()")
  public Object controllerMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
    if ( logger.isDebugEnabled() ) {
      logger.debug("The " + joinPoint.getSignature().getName() + "method is about to be execute at " +DateTime.now());
    }
    Object o = joinPoint.proceed(joinPoint.getArgs());
    if ( logger.isDebugEnabled() ) {
      logger.debug("The " + joinPoint.getSignature().getName() + "method is executed at " + DateTime.now());
    }
    return o;
  }
}
