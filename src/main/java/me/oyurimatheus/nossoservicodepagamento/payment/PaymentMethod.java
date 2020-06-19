package me.oyurimatheus.nossoservicodepagamento.payment;

public enum PaymentMethod {

    CREDIT_CARD(true, "A credit card used to pay your purchases online or in local"),
    CASH(false, "The good old money"),
    CARD_MACHINE(false, "Pay with all your cards"),
    CHECK(false, "A way to pay where places do not accept cards");

    private final boolean payOnline;
    private final String description;

    PaymentMethod(boolean payOnline, String description) {
        this.payOnline = payOnline;
        this.description = description;
    }

    /**
     *
     * @return a description of payment method chosen
     */
    public String description() {
        return description;
    };

    /**
     *
     * @return if payment method is able to be used online
     */
    boolean payOnline() {
        return payOnline;
    };
}
