package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;
import pl.training.shop.time.TimeProvider;


@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {

    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;


//    public PaymentProcessor(@Generator("uuid") PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentsRepository, TimeProvider timeProvider) {
//    public PaymentProcessor(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentsRepository, TimeProvider timeProvider) {
//        this.paymentIdGenerator = paymentIdGenerator;
//        this.paymentFeeCalculator = paymentFeeCalculator;
//        this.paymentsRepository = paymentsRepository;
//        this.timeProvider = timeProvider;
//    }

//    public PaymentProcessor(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentsRepository) {
//        this.paymentIdGenerator = paymentIdGenerator;
//        this.paymentFeeCalculator = paymentFeeCalculator;
//        this.paymentsRepository = paymentsRepository;
//        timeProvider = () -> Instant.now();
//    }

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
