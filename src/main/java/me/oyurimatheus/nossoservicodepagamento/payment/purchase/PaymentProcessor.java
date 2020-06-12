package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import org.springframework.stereotype.Service;

@Service
class PaymentProcessor {

    private final PaymentTransactionRepository paymentTransactionRepository;

    PaymentProcessor(PaymentTransactionRepository paymentTransactionRepository) {
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    public PaymentTransaction process(Payment payment) {

        PaymentTransaction transaction = new PaymentTransaction(payment);

        paymentTransactionRepository.save(transaction);

        return transaction;
    }
}
