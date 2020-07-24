package me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.Country;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.Payment;
import me.oyurimatheus.nossoservicodepagamento.payment.purchase.PaymentTransaction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "payment_gateways")
@Entity
public
class PaymentGateway {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "payment_gateway_id")
    private Long id;

    @Column(name = "gateway_name")
    private String gatewayName;

    @Column(name = "fare")
    private BigDecimal fare;

    @Enumerated(STRING)
    @Column(name = "fare_type")
    private FareType fareType;

    @Column(name = "gateway_uri")
    private String gatewayUri;

    @Enumerated(STRING)
    @Column(name = "country")
    private Country country;

    @ElementCollection(targetClass = CreditCardFlag.class)
    @CollectionTable(name = "payment_gateways_cards", joinColumns = @JoinColumn(name = "payment_gateway_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "card_flag")
    private Set<CreditCardFlag> cardFlags;

    /**
     * @deprecated hibernate eyes only
     */
    @Deprecated
    private PaymentGateway() { }

    public Optional<PaymentTransaction> pay(PaymentGatewayClient client, Payment payment) {
        if (!accept(payment)) {
            throw new IllegalArgumentException("this gateway does not accept this payment");
        }


        boolean paymentSuccessful = client.pay(URI.create(gatewayUri), new PaymentGatewayClient.PaymentGatewayRequest(payment));
        if (paymentSuccessful) {
            return Optional.of(PaymentTransaction.online(payment));
        }

        return Optional.empty();
    }

    public BigDecimal cost(Payment payment) {
        return fareType.calculate(fare, payment.getTotal());
    }


    public boolean accept(Payment payment) {
        return cardFlags.contains(payment.cardFlag());
    }

    public Optional<PaymentAttempt> forPayment(Payment payment) {
        if (accept(payment)) {
            return Optional.of(new PaymentAttempt(payment, this));
        }

        return Optional.empty();
    }
}
