package pl.training.shop;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import pl.training.shop.payments.*;
import pl.training.shop.time.SystemTimeProvider;
import pl.training.shop.time.TimeProvider;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ShopConfiguration {


    @Bean
    public TimeProvider timeProvider() {
        return new SystemTimeProvider();
    }

}
