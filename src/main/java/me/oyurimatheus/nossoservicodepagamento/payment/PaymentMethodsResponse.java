package me.oyurimatheus.nossoservicodepagamento.payment;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

class PaymentMethodsResponse {

    private final String id;
    private final String description;

    private PaymentMethodsResponse(PaymentMethod paymentMethod) {
        this.id = paymentMethod.name();
        this.description = paymentMethod.description();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    static Set<PaymentMethodsResponse> from(Set<PaymentMethod> paymentsAllowed) {
        return paymentsAllowed.stream()
                              .map(PaymentMethodsResponse::new)
                              .collect(toSet());
    }
}
