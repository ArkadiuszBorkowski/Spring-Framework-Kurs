package pl.training.shop.commons.aop;



import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(10)
@Component
@Aspect
@Log
public class ExecutionTimeLogger {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = System.nanoTime();
        var result = joinPoint.proceed();
        var totalTime = System.nanoTime() - startTime;
        log.info("Execution time %d ns for method %s".formatted(totalTime, joinPoint.getSignature()));
        return result;
    }

}
