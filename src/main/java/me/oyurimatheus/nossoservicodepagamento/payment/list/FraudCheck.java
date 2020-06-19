package me.oyurimatheus.nossoservicodepagamento.payment.list;

import me.oyurimatheus.nossoservicodepagamento.payment.User;

import java.util.Optional;

public interface FraudCheck {

    /**
     *
     * @param client who will be checked
     * @return  a possible {@link Fraud} if exists
     */
    Optional<Fraud> check(User client);
}
