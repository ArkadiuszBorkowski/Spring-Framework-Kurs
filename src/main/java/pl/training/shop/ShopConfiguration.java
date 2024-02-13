package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.training.shop.payments.PaymentFeeCalculator;
import pl.training.shop.payments.PercentagePaymentFeeCalculator;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ShopConfiguration {

    @Bean
    public PaymentFeeCalculator paymentFeeCalculator(){
        return new PercentagePaymentFeeCalculator(0.01);
    }

}
