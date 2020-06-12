package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "payment_transactions")
@Entity
class PaymentTransaction {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private PaymentTransactionStatus status;

    /**
     * @deprecated hibernate eyes only
     */
    @Deprecated
    private PaymentTransaction() { }

    PaymentTransaction(Payment payment) {

        this.payment = payment;
        this.status = PaymentTransactionStatus.transactionalStatusOf(payment);
    }

    public PaymentTransactionStatus getStatus() {
        return status;
    }
}
