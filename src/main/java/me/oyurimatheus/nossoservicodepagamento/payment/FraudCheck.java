package me.oyurimatheus.nossoservicodepagamento.payment;

import java.util.Optional;

interface FraudCheck {

    /**
     *
     * @param email client identification
     * @return  a possible {@link Fraud} if exists
     */
    Optional<Fraud> check(String email);
}
