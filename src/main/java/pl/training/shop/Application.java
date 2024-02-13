package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.Money;
import pl.training.shop.payments.PaymentProcessor;
import pl.training.shop.payments.PaymentRequest;

@Log
public class Application {

    private static final String DEFAULT_CURRENCY_CODE = "PLN";

    public static void main(String[] args) {
        var paymentService = new PaymentProcessor();
        var paymentRequest = new PaymentRequest(1L, Money.of(1_000, DEFAULT_CURRENCY_CODE));
        var payment = paymentService.process(paymentRequest);
        log.info(payment.toString());
    }

}
