package pl.training.shop.payments;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.Money;

import java.time.Instant;

@Builder
@Value
public class Payment {

    String id;
    Money value;
    Instant timestamp;
    PaymentStatus status;

}
