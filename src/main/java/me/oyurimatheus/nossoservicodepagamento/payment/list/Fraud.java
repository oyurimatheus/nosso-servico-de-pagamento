package me.oyurimatheus.nossoservicodepagamento.payment.list;

import me.oyurimatheus.nossoservicodepagamento.payment.User;

public class Fraud {


    private final User client;
    private final FraudType type;

    Fraud(FraudType type, User client) {
        this.type = type;
        this.client = client;
    }

    enum FraudType {
        EMAIL
    }
}
