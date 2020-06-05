package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
class SearchPaymentInDatabase {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private Set<FraudCheck> fraudsChecking;

    SearchPaymentInDatabase(RestaurantRepository restaurantRepository,
                            UserRepository userRepository,
                            Set<FraudCheck> fraudsChecking) {

        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.fraudsChecking = fraudsChecking;
    }

    public Set<PaymentMethod> findPaymentMethods(UserFavoriteRestaurants cachedUser) {

        var restaurant = restaurantRepository.findById(cachedUser.restaurantId()).get();
        var client = userRepository.findByEmail(cachedUser.userEmail()).get();

        return client.paymentMethodsTo(restaurant, fraudsChecking);
    }
}
