package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;

@RequiredArgsConstructor
public class PaymentFeeCalculator {

    private final double percentage;

    public Money calculateFee(Money paymentValue) {
        return paymentValue.multiply(percentage);
    }

}
