package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Order(100)
@Aspect
@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    @Pointcut("execution(* pl.training.shop.*.PaymentProcessor.proc*())")  //* pierwsza gwiazdka to typ zwracany
    //@Pointcut("@annotation(LogPayment)")
    //@Pointcut("bean(paymentService)")
    void process() {

    }

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";

    @Before(value = "process() && args(paymentRequest)")
    public void onStart(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        //joinPoint.getSignature();
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "process()", returning = "payment")
    public void onSuccess(Payment payment) {
        log.info(String.format("A new payment of %s has been created", payment.getValue()));
    }


    @AfterThrowing(value = "bean(paymentService)", throwing = "exception")
    public void onFailure(JoinPoint joinPoint, RuntimeException exception) {
        log.info(String.format("Payment processing failed: %s (method: %s) ", exception.getClass().getSimpleName(), joinPoint.getSignature().getName()));
    }

    @After(value = "bean(paymentService)")
    public void onFinish() {
        //joinPoint.getSignature();
        log.info("Payment processing complete : ");
    }

    private String createLogEntry(Payment payment) {
        return String.format(LOG_FORMAT, payment.getValue());
    }

    //@PostConstruct
    public void init() {
        log.info("Initializing payment");
    }

    //@PreDestroy
    public void destroy() {
        log.info("Destroying payment logger ");
    }
}
