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
    public Optional<Fraud> check(User client) {
        if (!possibleFraudsters.contains(client.getEmail())) {
            return Optional.empty();
        }

        return Optional.of(new Fraud(EMAIL, client));
    }
}
