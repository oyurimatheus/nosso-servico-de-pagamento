package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGateway;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

class OnlinePaymentAttemptsOrder {

    private Payment payment;
    private List<PaymentGateway> gatewayOrder;

    private OnlinePaymentAttemptsOrder(Payment payment, List<PaymentGateway> gatewayOrder) {
        this.payment = payment;
        this.gatewayOrder = gatewayOrder;
    }

    public static OnlinePaymentAttemptsOrder makeGatewaysAttemptsOrderTo(Payment payment, Set<PaymentGateway> gateways) {

        List<PaymentGateway> gatewayOrder = gateways.stream()
                                                    .filter(gateway -> gateway.accept(payment))
                                                    .sorted(Comparator.comparing(gateway -> gateway.cost(payment)))
                                                    .collect(toList());

        return new OnlinePaymentAttemptsOrder(payment, gatewayOrder);
    }

    public PaymentTransaction tryToPay() {
        Assert.notNull(payment, "payment must not be null");

        for (PaymentGateway gateway: gatewayOrder) {
            Optional<PaymentTransaction> possibleTransaction = gateway.pay(payment);
            if (possibleTransaction.isPresent()) {
                return possibleTransaction.get();
            }
        }

        return PaymentTransaction.failedOnlinePayment(payment);
    }
}
