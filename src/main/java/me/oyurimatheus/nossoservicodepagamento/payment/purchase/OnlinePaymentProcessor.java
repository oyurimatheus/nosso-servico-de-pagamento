package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGatewayClient;
import org.springframework.stereotype.Service;

@Service
public class OnlinePaymentProcessor {

    private final PaymentGatewayClient client;
    private final PaymentAttemptFactory paymentAttemptFactory;

    public OnlinePaymentProcessor(PaymentGatewayClient client,
                                  PaymentAttemptFactory paymentAttemptFactory) {
        this.paymentAttemptFactory = paymentAttemptFactory;
        this.client = client;
    }

    public PaymentTransaction tryToPay(Payment payment) {
        OnlinePaymentAttemptsOrder paymentOrder = OnlinePaymentAttemptsOrder.makeGatewaysAttemptsOrderTo(client, paymentAttemptFactory, payment);

        return paymentOrder.tryToPay();
    }
}
