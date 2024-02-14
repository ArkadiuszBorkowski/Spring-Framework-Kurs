package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Aspect
//@Scope(SCOPE_SINGLETON) //default
@Component
@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";


    @AfterReturning(value = "bean(paymentProcessor)", returning = "payment")
    public void log(Payment payment) {
        log.info(createLogEntry(payment));
    }

    private String createLogEntry(Payment payment) {
        return String.format(LOG_FORMAT, payment.getValue());
    }

    @PostConstruct
    public void init() {
        log.info("Initializing payment");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying payment logger ");
    }
}
