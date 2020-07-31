package me.oyurimatheus.nossoservicodepagamento.payment.list;

import me.oyurimatheus.nossoservicodepagamento.payment.PaymentMethod;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Set;

import static me.oyurimatheus.nossoservicodepagamento.payment.list.CacheProps.DEFAULT_CACHE_TIME;


@RedisHash(value = "usersFavoriteRestaurants", timeToLive = DEFAULT_CACHE_TIME)
class UserFavoriteRestaurants implements Serializable {

    private String id;
    private Set<PaymentMethod> paymentMethods;
    // here we should use an atomicInteger because of concurrence
    private int count;
    private String userEmail;
    private long restaurantId;

    /**
     * @deprecated framework eyes only
     */
    @Deprecated
    UserFavoriteRestaurants() { }

    public UserFavoriteRestaurants(String userEmail, long restaurantId) {
        this.id = userEmail + restaurantId;
        this.userEmail = userEmail;
        this.restaurantId = restaurantId;
    }

    public Set<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public int incrementAndGet() {
        return ++count;
    }

    public void updatePaymentMethods(Set<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public Long restaurantId() {
        return restaurantId;
    }

    public String userEmail() {
        return userEmail;
    }
}
