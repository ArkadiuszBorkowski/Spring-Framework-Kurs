package pl.training.shop.payments;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import pl.training.shop.time.TimeProvider;

@Configuration
public class PaymentsConfiguration {


    @Primary
    @Bean
    public PaymentIdGenerator uuidPaymentIdGenerator(){
        return new UuidPaymentIdGenerator();
    }

    @Bean
    public PaymentIdGenerator fakePaymentIdGenerator(){
        return new FakePaymentIdGenerator();
    }

    @Bean
    public PaymentFeeCalculator paymentFeeCalculator(){
        return new PercentagePaymentFeeCalculator(0.01);
    }


    @Bean
    public PaymentRepository paymentRepository() {
        return new InMemoryPaymentRepository();
    }

    @Bean
    public PaymentService paymentService( PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentRepository, TimeProvider timeProvider) {
        return new PaymentProcessor(paymentIdGenerator, paymentFeeCalculator, paymentRepository, timeProvider);
    }

    @Scope(BeanDefinition.SCOPE_SINGLETON)//default
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ConsolePaymentLogger paymentLogger() {
        return new ConsolePaymentLogger();
    }
}
