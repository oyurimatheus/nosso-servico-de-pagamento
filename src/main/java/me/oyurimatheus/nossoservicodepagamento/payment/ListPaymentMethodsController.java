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
    private final FraudCheckService fraudCheck;

    ListPaymentMethodsController(RestaurantRepository restaurantRepository,
                                 FraudCheckService fraudCheck) {
        this.restaurantRepository = restaurantRepository;
        this.fraudCheck = fraudCheck;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listPaymentMethodsToUser(@PathVariable("id") Long restaurantId,
                                                      @RequestParam("user_email") String client) {

        var restaurant = restaurantRepository.findById(restaurantId).get();

        Set<PaymentMethod> availablePaymentMethods = restaurantRepository.findPaymentMethodsAllowedTo(restaurantId, client);
        List<Fraud> frauds = fraudCheck.checkClient(client);

        Set<PaymentMethod> paymentMethodsAllowed = restaurant.paymentsAllowedTo(new PaymentsAvailable(frauds, availablePaymentMethods));

        Set<PaymentMethodsAllowedResponse> response = PaymentMethodsAllowedResponse.from(paymentMethodsAllowed);

        return ok(response);


    }
}
