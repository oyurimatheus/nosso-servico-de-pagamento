package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.oyurimatheus.nossoservicodepagamento.payment.PaymentMethod;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

class PaymentTransactionResponse {

    private String restaurant;
    private PaymentMethod paymentMethod;
    private Country country;
    @JsonInclude(NON_EMPTY)
    private String lastNumbers;
    private BigDecimal total;
    private PaymentTransactionStatus status;

    /**
     * @deprecated framework eyes only
     */
    @Deprecated
    public PaymentTransactionResponse() { }

    public PaymentTransactionResponse(PaymentTransaction transaction) {
        Payment payment = transaction.getPayment();
        this.restaurant = payment.getRestaurantName();
        this.paymentMethod = payment.getPaymentMethod();
        this.country = payment.getCountry();
        this.lastNumbers = payment.getCreditCardLast4Numbers();
        this.total = payment.getTotal();
        this.status = transaction.getStatus();
    }

    public static Object from(PaymentTransaction transaction) {
       return new PaymentTransactionResponse(transaction);
    }


    public String getRestaurant() {
        return restaurant;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Country getCountry() {
        return country;
    }

    public String getLastNumbers() {
        return lastNumbers;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public PaymentTransactionStatus getStatus() {
        return status;
    }
}
