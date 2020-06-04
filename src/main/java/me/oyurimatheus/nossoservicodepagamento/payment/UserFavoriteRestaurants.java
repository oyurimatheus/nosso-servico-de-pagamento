package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@RedisHash(value = "usersFavoriteRestaurants", timeToLive = 3600 * 4)
class UserFavoriteRestaurants implements Serializable {

    private String id;
    private Set<PaymentMethod> paymentMethods;
    // here we should use an atomicInteger because of concurrence
    private int count;

    /**
     * @deprecated framework eyes only
     */
    @Deprecated
    UserFavoriteRestaurants() { }

    public UserFavoriteRestaurants(String id) {
        this.id = id;
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
}
