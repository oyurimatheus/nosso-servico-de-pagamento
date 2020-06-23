package me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.Payment;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.PaymentTransaction;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentGateway {

    Optional<PaymentTransaction> pay(Payment payment);

    BigDecimal cost(Payment payment);

    boolean accept(Payment payment);
}
