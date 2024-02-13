package pl.training.shop.payments;

import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {

    @Setter
    private Map<String, Payment> payments = new HashMap<>();

    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }

}
