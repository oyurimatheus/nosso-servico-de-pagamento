package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

import static me.oyurimatheus.nossoservicodepagamento.payment.Fraud.FraudType.EMAIL;

@Component
class PossibleEmailAndOnlinePaymentFraudsterCheck implements FraudCheck {

    private static final Set<String> possibleFraudsters = Set.of("stich@toystory.com", "woody@toystory.com");

    @Override
    public Optional<Fraud> check(User client) {

        if (client.canPayOnline() && possibleFraudsters.contains(client.getEmail())) {
            return Optional.of(new Fraud(EMAIL, client));
        }

        return Optional.empty();
    }
}
