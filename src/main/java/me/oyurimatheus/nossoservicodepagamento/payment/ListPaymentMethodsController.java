package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/restaurants")
class ListPaymentMethodsController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final Set<FraudCheck> fraudChecks;

    ListPaymentMethodsController(RestaurantRepository restaurantRepository,
                                 UserRepository userRepository,
                                 Set<FraudCheck> fraudChecks) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.fraudChecks = fraudChecks;
    }

    @Cacheable(value = "paymentMethods", key = "#root.args[1]")
    @GetMapping("/{id}")
    public ResponseEntity<?> listPaymentMethodsToUser(@PathVariable("id") Long restaurantId,
                                                      @RequestParam("user_email") String email) {

        var restaurant = restaurantRepository.findById(restaurantId).get();
        var client = userRepository.findByEmail(email).get();

        Set<PaymentMethod> paymentMethodsAllowed = client.paymentMethodsTo(restaurant, fraudChecks);

        Set<PaymentMethodsResponse> response = PaymentMethodsResponse.from(paymentMethodsAllowed);

        return ok(response);
    }
}
