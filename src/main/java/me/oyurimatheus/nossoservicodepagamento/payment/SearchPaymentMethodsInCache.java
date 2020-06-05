package me.oyurimatheus.nossoservicodepagamento.payment;

import java.util.Set;

class SearchPaymentMethodsInCache implements FindPaymentMethods {

    private UserFavoriteRestaurants cachedUser;
    private FindPaymentMethods next;

    SearchPaymentMethodsInCache(UserFavoriteRestaurants cachedUser,
                                RestaurantRepository restaurantRepository,
                                UserRepository userRepository,
                                Set<FraudCheck> fraudsChecking) {

        this.cachedUser = cachedUser;
        next = new SearchPaymentInDatabase(cachedUser,
                                           restaurantRepository,
                                           userRepository,
                                           fraudsChecking);
    }

    @Override
    public Set<PaymentMethod> findPaymentMethods() {
        if (cachedUser.incrementAndGet() >= 10) {
            return cachedUser.getPaymentMethods();
        }

        return next.findPaymentMethods();
    }
}
