package pl.training.shop.payments;

import java.util.UUID;

public class PaymentIdGenerator {

    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
