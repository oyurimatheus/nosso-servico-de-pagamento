package me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.Country;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.Payment;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.PaymentTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.EnumSet;
import java.util.Optional;

import static me.oyurimatheus.nossoservicodepagamento.payment.purchase.Country.BR;
import static me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.CreditCardFlag.*;

@Service
class ShunGateway implements PaymentGateway {

    @Value("${nosso-servico-de-pagamentos.gateways.shun}")
    private BigDecimal fare;
    private final Country country = BR;
    private final PaymentGatewayClient client;
    private EnumSet<CreditCardFlag> cardFlags = EnumSet.of(VISA, MASTERCARD, ELO);

    ShunGateway(PaymentGatewayClient client) {
        this.client = client;
    }

    @Override
    public Optional<PaymentTransaction> pay(Payment payment) {
        boolean paymentSuccessful = client.pay(URI.create("http://localhost:8080/gateways/pay"), new PaymentGatewayClient.PaymentGatewayRequest(payment));
        if (paymentSuccessful) {
            return Optional.of(PaymentTransaction.online(payment));
        }

        return Optional.empty();
    }

    @Override
    public BigDecimal cost(Payment payment) {
        return fare;
    }

    @Override
    public boolean accept(Payment payment) {
        return cardFlags.contains(payment.cardFlag());
    }
}
