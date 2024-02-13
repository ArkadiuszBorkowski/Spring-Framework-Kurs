package pl.training.shop.payments;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
