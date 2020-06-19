package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.PaymentMethod;
import me.oyurimatheus.nossoservicodepagamento.payment.Restaurant;
import me.oyurimatheus.nossoservicodepagamento.payment.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

import static me.oyurimatheus.nossoservicodepagamento.payment.PaymentMethod.CREDIT_CARD;

class PaymentRequest {

    @NotNull
    private PaymentMethod paymentMethod;

    @Min(1)
    @NotNull
    private Long restaurantId;

    @NotBlank
    @Email
    private String userEmail;

    @NotNull
    @Min(0)
    private BigDecimal total;

    @NotNull
    private Country country;

    @NotBlank
    private String purchaseId;

    private String creditCardNumber;

    PaymentRequest() { }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Country getCountry() {
        return country;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public Payment toPayment(Function<Long, Optional<Restaurant>> findRestaurantById,
                             Function<String, Optional<User>> findUserById) {

        Optional<Restaurant> restaurant = findRestaurantById.apply(restaurantId);
        Optional<User> user = findUserById.apply(userEmail);

        if (paymentMethod == CREDIT_CARD) {
            return Payment.makeOnlinePayment(restaurant.get(), user.get(), total, purchaseId, country, creditCardNumber);
        }

        return Payment.makeOfflinePayment(restaurant.get(), user.get(), total, purchaseId, country, paymentMethod);
    }

    public boolean isOnline() {
        return CREDIT_CARD == paymentMethod;
    }

    public boolean hasCreditCardNumber() {
        return creditCardNumber != null && !creditCardNumber.isBlank();
    }

    public boolean isPaymentOnlineInvalid() {
        return isOnline() && !hasCreditCardNumber();
    }
}
