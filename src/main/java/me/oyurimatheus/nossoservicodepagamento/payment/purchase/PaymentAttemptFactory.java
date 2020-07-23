package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentAttempt;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGateway;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGatewayRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
class PaymentAttemptFactory {

    private final PaymentGatewayRepository repository;

    PaymentAttemptFactory(PaymentGatewayRepository repository) {
        this.repository = repository;
    }


    public List<PaymentAttempt> makeAttempts(Payment payment) {
        List<PaymentGateway> gateways = repository.findAll();

        return gateways.stream()
                       .map(gateway -> gateway.forPayment(payment))
                       .filter(Optional::isPresent)
                       .map(Optional::get)
                       .collect(toList());
    }
}
