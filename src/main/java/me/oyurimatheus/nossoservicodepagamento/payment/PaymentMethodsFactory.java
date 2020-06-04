package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Component
class PaymentMethodsFactory {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final PaymentMethodsRepository paymentMethodsRepository;
    private final Set<FraudCheck> fraudsChecking;

    PaymentMethodsFactory(RestaurantRepository restaurantRepository,
                          UserRepository userRepository,
                          PaymentMethodsRepository paymentMethodsRepository,
                          Set<FraudCheck> fraudsChecking) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.paymentMethodsRepository = paymentMethodsRepository;
        this.fraudsChecking = fraudsChecking;
    }


    @Transactional
    public Set<PaymentMethod> getPaymentMethods(Long restaurantId, String email) {
        UserFavoriteRestaurants cachedUser = getCachedUser(restaurantId, email);

        if (cachedUser.incrementAndGet() >= 10) {
            return cachedUser.getPaymentMethods();
        }

        var restaurant = restaurantRepository.findById(restaurantId).get();
        var client = userRepository.findByEmail(email).get();

        Set<PaymentMethod> paymentMethods = client.paymentMethodsTo(restaurant, fraudsChecking);
        cachedUser.updatePaymentMethods(paymentMethods);
        paymentMethodsRepository.save(cachedUser);

        return paymentMethods;

    }

    private UserFavoriteRestaurants getCachedUser(Long restaurantId, String email) {
        // Maybe we need to create a value object to represent the id
        Optional<UserFavoriteRestaurants> possibleUser = paymentMethodsRepository.findById(email + restaurantId);

        if (possibleUser.isEmpty()) {
            return new UserFavoriteRestaurants(email + restaurantId);
        }

        return possibleUser.get();
    }
}
