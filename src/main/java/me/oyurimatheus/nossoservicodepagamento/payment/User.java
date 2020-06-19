package me.oyurimatheus.nossoservicodepagamento.payment;

import me.oyurimatheus.nossoservicodepagamento.payment.list.FraudCheck;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static javax.persistence.GenerationType.IDENTITY;
import static me.oyurimatheus.nossoservicodepagamento.payment.PaymentMethod.CREDIT_CARD;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Email
    @NotBlank
    @Column(name = "email")
    private String email;

    @ElementCollection(targetClass = PaymentMethod.class)
    @CollectionTable(name = "user_payment_methods", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    @Min(1)
    private Set<PaymentMethod> paymentMethods;

    /**
     * @deprecated hibernate eyes only
     */
    @Deprecated
    private User() { }


    public String getEmail() {
        return email;
    }

    public Set<PaymentMethod> paymentMethodsTo(Restaurant restaurant, Set<FraudCheck> fraudsChecking) {
        Set<PaymentMethod> paymentAvailable = restaurant.paymentsAvailableTo(paymentMethods);

        boolean hasFraud = fraudsChecking.stream()
                                         .map(fraudCheck -> fraudCheck.check(this))
                                         .anyMatch(Optional::isPresent);

        if (hasFraud) {
            return paymentAvailable.stream()
                                   .filter(method -> !method.payOnline())
                                   .collect(toSet());
        }

        return paymentAvailable;
    }

    public boolean canPayOnline() {
        return paymentMethods.contains(CREDIT_CARD);
    }
}
