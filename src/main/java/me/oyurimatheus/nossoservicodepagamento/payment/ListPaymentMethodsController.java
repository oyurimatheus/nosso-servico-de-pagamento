package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/restaurants")
class ListPaymentMethodsController {

    private final RestaurantRepository restaurantRepository;

    ListPaymentMethodsController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listPaymentMethodsToUser(@PathVariable("id") Long restaurantId,
                                                      @RequestParam("user_email") String user) {

        Set<PaymentMethod> paymentsAllowed = restaurantRepository.findPaymentMethodsAllowedTo(restaurantId, user);

        var response = PaymentMethodsAllowedResponse.from(paymentsAllowed);

        return ok(response);
    }
}
