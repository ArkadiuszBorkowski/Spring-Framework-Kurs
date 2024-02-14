package pl.training.shop.payments;

import org.springframework.stereotype.Component;

@Generator("fake")
public class FakePaymentIdGenerator implements PaymentIdGenerator{

    private static final String ID = "1";

    @Override
    public String getNext() {
        return ID;
    }
}
