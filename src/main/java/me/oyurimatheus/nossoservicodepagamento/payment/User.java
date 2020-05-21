package me.oyurimatheus.nossoservicodepagamento.payment;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "users")
@Entity
class User {

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


}
