package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentAttempt;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGateway;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGatewayClient;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGatewayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class OnlinePaymentProcessor {

    private final PaymentGatewayRepository paymentGatewayRepository;
    private final PaymentGatewayClient client;

    public OnlinePaymentProcessor(PaymentGatewayRepository paymentGatewayRepository,
                                  PaymentGatewayClient client) {
        this.paymentGatewayRepository = paymentGatewayRepository;
        this.client = client;
    }

    public PaymentTransaction tryToPay(Payment payment) {
        List<PaymentGateway> gateways = paymentGatewayRepository.findAll();
        List<PaymentAttempt> paymentAttempts = gateways.stream()
                                                       .map(gateway -> gateway.forPayment(payment))
                                                       .filter(Optional::isPresent)
                                                       .map(Optional::get)
                                                       .collect(toList());

        OnlinePaymentAttemptsOrder paymentOrder = OnlinePaymentAttemptsOrder.makeGatewaysAttemptsOrderTo(client, paymentAttempts);

        return paymentOrder.tryToPay();
    }
}
