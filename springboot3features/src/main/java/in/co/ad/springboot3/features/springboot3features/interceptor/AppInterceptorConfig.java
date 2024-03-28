package in.co.ad.springboot3.features.springboot3features.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
/* import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut; */
import org.springframework.aop.framework.Advised;
/* import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean; */
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class AppInterceptorConfig {
    /* public @Bean CustomizableTraceInterceptor interceptor() {

        var interceptor = new CustomizableTraceInterceptor();
        interceptor.setEnterMessage("Entering $[methodName]($[arguments]).");
        interceptor.setExitMessage(
                "Leaving $[methodName](..) with return value $[returnValue], took $[invocationTime]ms.");

        return interceptor;
    }

    public @Bean Advisor traceAdvisor() {

        var pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public * org.springframework.data.repository.CrudRepository+.*(..))");

        return new DefaultPointcutAdvisor(pointcut, interceptor());
    }
 */
    @Before("execution(* in.co.ad.springboot3.features.springboot3features.repository.*.*(*)) && target(bean)")
    public void getRepositoryName(JoinPoint jp, Object bean) throws Exception {
        Advised advised = (Advised) bean;
        for (Class<?> clazz : advised.getProxiedInterfaces())
            System.out.println(clazz);
    }
}
