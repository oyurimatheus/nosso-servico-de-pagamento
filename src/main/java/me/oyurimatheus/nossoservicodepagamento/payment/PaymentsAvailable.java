package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.springframework.util.Assert.hasText;

class PaymentsAvailable {

    private final List<Fraud> frauds;
    private final Set<PaymentMethod> availablePaymentMethods;

    PaymentsAvailable(@NotEmpty List<Fraud> frauds,
                      @NotEmpty Set<PaymentMethod> availablePaymentMethods) {

        collectionIsNotEmpty(frauds, "frauds must have at least one element");
        collectionIsNotEmpty(availablePaymentMethods, "availablePaymentMethods must have at least one element");

        this.frauds = frauds;
        this.availablePaymentMethods = availablePaymentMethods;
    }

    private void collectionIsNotEmpty(Collection<?> collection, String message) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public boolean hasFrauds() {
        return frauds.size() > 0;
    }

    public Set<PaymentMethod> availablePaymentMethods() {
        return availablePaymentMethods;
    }

    public Set<PaymentMethod> offlinePaymentMethods() {
        return availablePaymentMethods.stream()
                                      .filter(method -> !method.payOnline())
                                      .collect(toSet());
    }
}
