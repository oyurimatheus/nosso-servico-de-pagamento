package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentAttempt;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGatewayClient;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

class OnlinePaymentAttemptsOrder {

    private List<PaymentAttempt> paymentAttemptOrder;
    private PaymentGatewayClient client;
    private Payment payment;

    private OnlinePaymentAttemptsOrder(List<PaymentAttempt> paymentAttemptOrder,
                                       PaymentGatewayClient client,
                                       Payment payment) {
        this.paymentAttemptOrder = paymentAttemptOrder;
        this.client = client;
        this.payment = payment;
    }

    public static OnlinePaymentAttemptsOrder makeGatewaysAttemptsOrderTo(PaymentGatewayClient client,
                                                                         PaymentAttemptFactory factory,
                                                                         Payment payment) {

        List<PaymentAttempt> attempts = factory.makeAttempts(payment);

        List<PaymentAttempt> gatewayOrder = attempts.stream()
                                                    .filter(PaymentAttempt::accept)
                                                    .sorted(Comparator.comparing(PaymentAttempt::cost))
                                                    .collect(toList());

        return new OnlinePaymentAttemptsOrder(gatewayOrder, client, payment);
    }

    public PaymentTransaction tryToPayAsync() {
        for (PaymentAttempt attempt: paymentAttemptOrder) {
            Optional<PaymentTransaction> possibleTransaction = attempt.payAsync(client);
            if (possibleTransaction.isPresent()) {
                return possibleTransaction.get();
            }
        }

        return PaymentTransaction.failedOnlinePayment(payment);
    }
}
