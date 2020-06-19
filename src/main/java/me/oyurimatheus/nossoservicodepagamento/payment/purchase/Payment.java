package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.PaymentMethod;
import me.oyurimatheus.nossoservicodepagamento.payment.Restaurant;
import me.oyurimatheus.nossoservicodepagamento.payment.User;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static me.oyurimatheus.nossoservicodepagamento.payment.PaymentMethod.CREDIT_CARD;
import static me.oyurimatheus.nossoservicodepagamento.payment.purchase.PaymentStatus.ESPERANDO_OFFLINE;
import static me.oyurimatheus.nossoservicodepagamento.payment.purchase.PaymentStatus.ESPERANDO_ONLINE;
import static org.springframework.util.Assert.*;

@Table(name = "payments")
@Entity
class Payment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Restaurant restaurant;

    @NotNull
    @OneToOne
    private User user;

    @NotNull
    @Min(0)
    private BigDecimal total;

    @NotEmpty
    @Column(unique = true)
    private String purchaseId;

    @NotNull
    @Enumerated(STRING)
    private Country country;

    @NotNull
    @Enumerated(STRING)
    private PaymentMethod paymentMethod;

    // TODO: this number must be encrypted
    private String creditCardNumber;

    @Enumerated(STRING)
    private PaymentStatus status;

    /**
     * @deprecated hibernate eyes only
     */
    @Deprecated
    private Payment() { }

    /**
     * Use this constructor if and only if paymentMethod is equal to {@link CREDIT_CARD}
     *
     * @param restaurant which purchase was made
     * @param user who purchase the food
     * @param total the total amount of purchase
     * @param country the country code
     * @param creditCardNumber the credit card number for online payment
     *
     * @throws IllegalArgumentException if any argument is not satisfied
     */
    private Payment(@NotNull Restaurant restaurant,
                   @NotNull User user,
                   @NotNull @Min(0) BigDecimal total,
                   @NotEmpty String purchaseId,
                   @NotNull Country country,
                   @NotEmpty String creditCardNumber) {

        notNull(restaurant, "restaurant must not be null");
        notNull(user, "user must not be null");
        greaterThanZero(total, "total must be a positive value");
        notBlank(purchaseId, "purchaseId must not be blank");
        notNull(country, "restaurant must not be null");
        notBlank(creditCardNumber, "creditCardNumber must not be blank");

        this.restaurant = restaurant;
        this.user = user;
        this.total = total;
        this.purchaseId = purchaseId;
        this.country = country;
        this.paymentMethod = CREDIT_CARD;
        this.creditCardNumber = creditCardNumber;
        status = ESPERANDO_ONLINE;
    }

    /**
     * Use this constructor if and only if paymentMethod is not equal to {@link CREDIT_CARD}
     *
     * @param restaurant which purchase was made
     * @param user who purchase the food
     * @param total the total amount of purchase
     * @param country the country code
     * @param paymentMethod payment method offline
     *
     * @throws IllegalArgumentException if any argument is not satisfied
     */
    private Payment(@NotNull Restaurant restaurant,
                   @NotNull User user,
                   @NotNull @Min(1) BigDecimal total,
                   @NotEmpty String purchaseId,
                   @NotNull Country country,
                   @NotNull PaymentMethod paymentMethod) {

        notNull(restaurant, "restaurant must not be null");
        notNull(user, "user must not be null");
        greaterThanZero(total, "total must be a positive value");
        notBlank(purchaseId, "purchaseId must not be blank");
        notNull(country, "restaurant must not be null");
        notNull(paymentMethod, "paymentMethod must not be null");

        isTrue(!paymentMethod.equals(CREDIT_CARD),"Online payment method must use the ");

        this.restaurant = restaurant;
        this.user = user;
        this.total = total;
        this.purchaseId = purchaseId;
        this.country = country;
        this.paymentMethod = paymentMethod;
        status = ESPERANDO_OFFLINE;
    }

    public boolean isOnline() {
        return CREDIT_CARD == paymentMethod;
    }

    static Payment makeOnlinePayment(@NotNull Restaurant restaurant,
                              @NotNull User user,
                              @NotNull @Min(0) BigDecimal total,
                              @NotEmpty String purchaseId,
                              @NotNull Country country,
                              @NotEmpty String creditCardNumber) {

        return new Payment(restaurant, user, total, purchaseId, country, creditCardNumber);
    }

    static Payment makeOfflinePayment(@NotNull Restaurant restaurant,
                               @NotNull User user,
                               @NotNull @Min(1) BigDecimal total,
                               @NotEmpty String purchaseId,
                               @NotNull Country country,
                               @NotNull PaymentMethod paymentMethod) {


        return new Payment(restaurant, user, total, purchaseId, country, paymentMethod);
    }

    private void greaterThanZero(BigDecimal value, String msg) {
        if (value == null || value.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException(msg);
        }
    }

    private void notBlank(String str, String msg) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException(msg);
        }
    }

    public String getPurchaseId() {
        return purchaseId;
    }
}
