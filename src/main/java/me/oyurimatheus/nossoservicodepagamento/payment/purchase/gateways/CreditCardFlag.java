package me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways;

public enum CreditCardFlag {

    VISA,
    MASTERCARD,
    HYPERCARD,
    ELO;

    public static CreditCardFlag flagFrom(String creditCardNumber) {
        // TODO: Maybe in the future we need to change the way we get the flag
        if (creditCardNumber.startsWith("1111"))
            return VISA;

        if (creditCardNumber.startsWith("222"))
            return MASTERCARD;

        if (creditCardNumber.startsWith("3333"))
            return HYPERCARD;

        if (creditCardNumber.startsWith("4444"))
            return ELO;

        throw new IllegalArgumentException("Credit card in an invalid format");
    }
}
