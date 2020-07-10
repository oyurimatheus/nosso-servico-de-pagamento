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

    private OnlinePaymentAttemptsOrder(List<PaymentAttempt> paymentAttemptOrder, PaymentGatewayClient client) {
        this.paymentAttemptOrder = paymentAttemptOrder;
        this.client = client;
    }

    public static OnlinePaymentAttemptsOrder makeGatewaysAttemptsOrderTo(PaymentGatewayClient client,
                                                                         List<PaymentAttempt> attempts) {



        List<PaymentAttempt> gatewayOrder = attempts.stream()
                                                    .filter(PaymentAttempt::accept)
                                                    .sorted(Comparator.comparing(PaymentAttempt::cost))
                                                    .collect(toList());

        return new OnlinePaymentAttemptsOrder(gatewayOrder, client);
    }

    public PaymentTransaction tryToPay() {
        for (PaymentAttempt attempt: paymentAttemptOrder) {
            Optional<PaymentTransaction> possibleTransaction = attempt.pay(client);
            if (possibleTransaction.isPresent()) {
                return possibleTransaction.get();
            }
        }

        PaymentAttempt attempt = paymentAttemptOrder.get(0);
        return PaymentTransaction.failedOnlinePayment(attempt.getPayment());
    }
}
