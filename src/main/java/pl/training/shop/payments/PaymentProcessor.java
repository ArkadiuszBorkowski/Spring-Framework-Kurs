package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.training.shop.time.TimeProvider;

import java.time.Instant;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;


@Service
public class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @Autowired
//    public PaymentProcessor(@Generator("uuid") PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentsRepository, TimeProvider timeProvider) {
    public PaymentProcessor(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentsRepository, TimeProvider timeProvider) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.paymentFeeCalculator = paymentFeeCalculator;
        this.paymentsRepository = paymentsRepository;
        this.timeProvider = timeProvider;
    }

    public PaymentProcessor(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentsRepository) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.paymentFeeCalculator = paymentFeeCalculator;
        this.paymentsRepository = paymentsRepository;
        timeProvider = () -> Instant.now();
    }

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var paymentValue = calculatePaymentValue(paymentRequest.getValue());
        var payment = createPayment(paymentValue);
        return paymentsRepository.save(payment);
    }

    private Payment createPayment(Money paymentValue) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(timeProvider.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
    }

    private Money calculatePaymentValue(Money paymentValue) {
        var paymentFee = paymentFeeCalculator.calculateFee(paymentValue);
        return paymentValue.add(paymentFee);
    }

}
