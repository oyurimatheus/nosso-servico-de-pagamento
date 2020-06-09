package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class PaymentMethodCacheService {

    private final PaymentMethodsRepository paymentMethodsRepository;

    PaymentMethodCacheService(PaymentMethodsRepository paymentMethodsRepository) {
        this.paymentMethodsRepository = paymentMethodsRepository;
    }

    public UserFavoriteRestaurants getCachedUser(Long restaurantId, String email) {
        // Maybe we need to create a value object to represent the id
        Optional<UserFavoriteRestaurants> possibleUser = paymentMethodsRepository.findById(email + restaurantId);

        if (possibleUser.isEmpty()) {
            return new UserFavoriteRestaurants(email, restaurantId);
        }

        return possibleUser.get();
    }

    public void updateCache(UserFavoriteRestaurants cachedUser) {
        paymentMethodsRepository.save(cachedUser);
    }
}
