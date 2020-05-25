package me.oyurimatheus.nossoservicodepagamento.payment;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

class PaymentMethodsAllowedResponse {

    private final String id;
    private final String description;

    PaymentMethodsAllowedResponse(PaymentMethod paymentMethod) {
        this.id = paymentMethod.name();
        this.description = paymentMethod.description();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    static Set<PaymentMethodsAllowedResponse> from(Set<PaymentMethod> paymentsAllowed) {
        return paymentsAllowed.stream()
                              .map(PaymentMethodsAllowedResponse::new)
                              .collect(toSet());
    }
}
