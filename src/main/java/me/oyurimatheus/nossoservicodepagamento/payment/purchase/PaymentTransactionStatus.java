package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

enum PaymentTransactionStatus {
    ESPERANDO_ONLINE,
    ESPERANDO_OFFLINE;


    public static PaymentTransactionStatus transactionalStatusOf(Payment payment) {
        if (payment.isOnline()) {
            return ESPERANDO_ONLINE;
        }

        return ESPERANDO_OFFLINE;
    }
}
