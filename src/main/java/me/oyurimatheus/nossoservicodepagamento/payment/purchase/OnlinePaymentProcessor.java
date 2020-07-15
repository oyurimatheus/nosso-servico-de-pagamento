package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentAttempt;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGatewayClient;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGatewayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
