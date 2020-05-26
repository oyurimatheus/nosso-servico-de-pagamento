package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/restaurants")
class ListPaymentMethodsController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final FraudCheckService fraudCheck;

    ListPaymentMethodsController(RestaurantRepository restaurantRepository,
                                 UserRepository userRepository,
                                 FraudCheckService fraudCheck) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.fraudCheck = fraudCheck;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listPaymentMethodsToUser(@PathVariable("id") Long restaurantId,
                                                      @RequestParam("user_email") String email) {

        var restaurant = restaurantRepository.findById(restaurantId).get();
        var client = userRepository.findByEmail(email).get();

        List<Fraud> frauds = fraudCheck.checkClient(client);

        Set<PaymentMethod> paymentMethodsAllowed = client.paymentMethodsTo(restaurant, frauds);

        Set<PaymentMethodsResponse> response = PaymentMethodsResponse.from(paymentMethodsAllowed);

        return ok(response);
    }
}
