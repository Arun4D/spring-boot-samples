package in.co.ad.springboot3.features.springboot3features.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
@Component
public class AroundExample {
    //@Around("execution(* in.co.ad.springboot3.features.springboot3features.repository.*.*(..))")
    @Around("execution(* in.co.ad.springboot3.features.springboot3features.repository.*Repository.*(..))")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		// start stopwatch
		Object retVal = pjp.proceed();
		// stop stopwatch
		return retVal;
	}
}
