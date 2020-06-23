package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
class PaymentProcessor {

    private final PaymentTransactionRepository paymentTransactionRepository;
    private final Set<PaymentGateway> gateways;

    PaymentProcessor(PaymentTransactionRepository paymentTransactionRepository,
                     Set<PaymentGateway> gateways) {
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.gateways = gateways;
    }

    public PaymentTransaction process(Payment payment) {

        if (!payment.isOnline()) {
            verifyTransactionAlreadyExist(payment);

            PaymentTransaction transaction = PaymentTransaction.offline(payment);
            paymentTransactionRepository.save(transaction);
            return transaction;
        }

        OnlinePaymentAttemptsOrder paymentOrder = OnlinePaymentAttemptsOrder.makeGatewaysAttemptsOrderTo(payment, gateways);

        PaymentTransaction transaction = paymentOrder.tryToPay(payment);
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
