package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;

@RequiredArgsConstructor
public class PercentagePaymentFeeCalculator implements PaymentFeeCalculator {

    private final double percentage;

    @Override
    public Money calculateFee(Money paymentValue) {
        return paymentValue.multiply(percentage);
    }

}
