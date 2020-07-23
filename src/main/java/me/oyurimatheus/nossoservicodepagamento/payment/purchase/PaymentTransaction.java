package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static me.oyurimatheus.nossoservicodepagamento.payment.purchase.PaymentTransactionStatus.*;

@Table(name = "payment_transactions")
@Entity
public class PaymentTransaction {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @NotNull
    @Enumerated(STRING)
    private PaymentTransactionStatus status;

    /**
     * @deprecated hibernate eyes only
     */
    @Deprecated
    private PaymentTransaction() { }

    private PaymentTransaction(Payment payment, PaymentTransactionStatus status) {
        this.payment = payment;
        this.status = status;
    }

    public static PaymentTransaction offline(Payment payment) {
        return new PaymentTransaction(payment, ESPERANDO_OFFLINE);
    }

    public static PaymentTransaction online(Payment payment) {
        return new PaymentTransaction(payment, CONCLUIDA);
    }

    public static PaymentTransaction failedOnlinePayment(Payment payment) {
        return new PaymentTransaction(payment, FALHA);
    }

    public PaymentTransactionStatus getStatus() {
        return status;
    }
}
