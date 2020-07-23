package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class PaymentProcessor {

    private final PaymentTransactionRepository paymentTransactionRepository;
    private final OnlinePaymentProcessor onlinePaymentProcessor;

    public PaymentProcessor(PaymentTransactionRepository paymentTransactionRepository,
                            OnlinePaymentProcessor onlinePaymentProcessor) {
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.onlinePaymentProcessor = onlinePaymentProcessor;
    }

    public PaymentTransaction process(Payment payment) {

        if (!payment.isOnline()) {
            verifyTransactionAlreadyExist(payment);

            PaymentTransaction transaction = PaymentTransaction.offline(payment);
            paymentTransactionRepository.save(transaction);
            return transaction;
        }

        PaymentTransaction transaction = onlinePaymentProcessor.tryToPay(payment);
        paymentTransactionRepository.save(transaction);

        return transaction;
    }

    private void verifyTransactionAlreadyExist(Payment payment) {
        Optional<PaymentTransaction> possibleTransaction = paymentTransactionRepository.findByPurchaseId(payment.getPurchaseId());
        if (possibleTransaction.isPresent()) {
            throw new IllegalStateException("Offline payment already has a transaction");
        }
    }
}
