package me.oyurimatheus.nossoservicodepagamento.payment;

class Fraud {


    private final String client;
    private final FraudType type;

    Fraud(FraudType type, String email) {
        this.type = type;
        this.client = email;
    }

    enum FraudType {
        EMAIL
    }
}
