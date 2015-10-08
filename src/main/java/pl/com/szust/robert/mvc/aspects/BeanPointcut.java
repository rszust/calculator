package pl.com.szust.robert.mvc.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BeanPointcut {
    @Pointcut("execution(public * *(..))")
	public int isPublic() {
		return 1;
	};
}
