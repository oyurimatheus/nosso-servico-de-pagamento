package me.oyurimatheus.nossoservicodepagamento.payment.list;

import me.oyurimatheus.nossoservicodepagamento.payment.PaymentMethod;
import me.oyurimatheus.nossoservicodepagamento.payment.RestaurantRepository;
import me.oyurimatheus.nossoservicodepagamento.payment.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
class SearchPaymentInDatabase {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final Set<FraudCheck> fraudsChecking;

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
