package pl.com.szust.robert.mvc.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitecture {
  @Pointcut("within(pl.com.szust.robert.mvc.controllers..*)")
  public void inControllerLayer() {};
}
