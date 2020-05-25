package me.oyurimatheus.nossoservicodepagamento.payment;

import me.oyurimatheus.nossoservicodepagamento.payment.Fraud.FraudType;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

import static me.oyurimatheus.nossoservicodepagamento.payment.Fraud.FraudType.*;

@Component
class PossibleEmailFraudsterCheck implements FraudCheck{

    private static final Set<String> possibleFraudsters = Set.of("stich@toystory.com", "woody@toystory.com");

    @Override
    public Optional<Fraud> check(String email) {
        if (!possibleFraudsters.contains(email)) {
            return Optional.empty();
        }

        return Optional.of(new Fraud(EMAIL, email));
    }
}
