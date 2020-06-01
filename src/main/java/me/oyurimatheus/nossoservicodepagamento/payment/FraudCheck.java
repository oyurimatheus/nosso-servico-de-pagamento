package me.oyurimatheus.nossoservicodepagamento.payment;

import java.util.Optional;

interface FraudCheck {

    /**
     *
     * @param client who will be checked
     * @return  a possible {@link Fraud} if exists
     */
    Optional<Fraud> check(User client);
}
