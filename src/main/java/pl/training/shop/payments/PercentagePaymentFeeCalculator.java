package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
public class PercentagePaymentFeeCalculator implements PaymentFeeCalculator {

    private final double percentage;

    @Override
    public Money calculateFee(Money paymentValue) {
        return paymentValue.multiply(percentage);
    }

}
