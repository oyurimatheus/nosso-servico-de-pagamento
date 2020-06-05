package me.oyurimatheus.nossoservicodepagamento.payment;

import java.util.Set;

class SearchPaymentInDatabase implements FindPaymentMethods {

    private UserFavoriteRestaurants cachedUser;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private Set<FraudCheck> fraudsChecking;

    SearchPaymentInDatabase(UserFavoriteRestaurants cachedUser,
                            RestaurantRepository restaurantRepository,
                            UserRepository userRepository,
                            Set<FraudCheck> fraudsChecking) {

        this.cachedUser = cachedUser;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.fraudsChecking = fraudsChecking;
    }

    @Override
    public Set<PaymentMethod> findPaymentMethods() {

        var restaurant = restaurantRepository.findById(cachedUser.restaurantId()).get();
        var client = userRepository.findByEmail(cachedUser.userEmail()).get();

        return client.paymentMethodsTo(restaurant, fraudsChecking);
    }
}
