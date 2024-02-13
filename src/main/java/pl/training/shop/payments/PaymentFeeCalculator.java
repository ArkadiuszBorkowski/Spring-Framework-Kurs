package pl.training.shop.payments;

import org.javamoney.moneta.Money;

public interface PaymentFeeCalculator {

    Money calculateFee(Money paymentValue);

}
