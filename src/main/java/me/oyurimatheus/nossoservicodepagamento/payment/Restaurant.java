package me.oyurimatheus.nossoservicodepagamento.payment;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "restaurants")
@Entity
class Restaurant {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @ElementCollection(targetClass = PaymentMethod.class)
    @CollectionTable(name = "restaurants_payment_methods", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    @Min(1)
    private Set<PaymentMethod> paymentMethods;

    /**
     * @deprecated hibernate eyes only
     */
    @Deprecated
    Restaurant() { }
}
