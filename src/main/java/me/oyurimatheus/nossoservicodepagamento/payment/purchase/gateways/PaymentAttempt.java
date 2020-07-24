package me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.Payment;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.PaymentTransaction;

import java.math.BigDecimal;
import java.util.Optional;

public class PaymentAttempt {

    private final Payment payemnt;
    private final PaymentGateway gateway;

    public PaymentAttempt(Payment payment, PaymentGateway gateway) {
        if (!gateway.accept(payment)) {
            throw new IllegalStateException("Gateway does not accept this payment");
        }
        this.payemnt = payment;
        this.gateway = gateway;
    }

    public boolean accept() {
        return gateway.accept(payemnt);
    }

    public BigDecimal cost() {
        return gateway.cost(payemnt);
    }

    public Optional<PaymentTransaction> pay(PaymentGatewayClient client) {
        return gateway.pay(client, payemnt);
    }

    public Payment getPayment() {
        return payemnt;
    }
}
