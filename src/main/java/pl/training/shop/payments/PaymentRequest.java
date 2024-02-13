package pl.training.shop.payments;

import lombok.Value;
import org.javamoney.moneta.Money;

@Value
public class PaymentRequest {

    Long id;
    Money value;

}
