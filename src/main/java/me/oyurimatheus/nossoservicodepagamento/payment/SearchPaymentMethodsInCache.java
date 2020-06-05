package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
class SearchPaymentMethodsInCache {

    private final PaymentMethodCacheService paymentCache;
    private final SearchPaymentInDatabase searchPaymentInDatabase;

    SearchPaymentMethodsInCache(PaymentMethodCacheService paymentCache,
                                SearchPaymentInDatabase searchPaymentInDatabase) {
        this.paymentCache = paymentCache;
        this.searchPaymentInDatabase = searchPaymentInDatabase;
    }

    public Set<PaymentMethod> findPaymentMethods(UserFavoriteRestaurants cachedUser) {
        if (cachedUser.incrementAndGet() >= 10) {
            return cachedUser.getPaymentMethods();
        }

        Set<PaymentMethod> paymentMethods = searchPaymentInDatabase.findPaymentMethods(cachedUser);
        cachedUser.updatePaymentMethods(paymentMethods);
        paymentCache.updateCache(cachedUser);

        return paymentMethods;
    }
}
