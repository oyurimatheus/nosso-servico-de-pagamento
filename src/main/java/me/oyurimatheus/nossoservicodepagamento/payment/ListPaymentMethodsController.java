package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/restaurants")
class ListPaymentMethodsController {

    private final PaymentMethodsFactory paymentMethodsFactory;
    private final PaymentMethodsRepository paymentMethodsRepository;

    ListPaymentMethodsController(PaymentMethodsFactory paymentMethodsFactory,
                                 PaymentMethodsRepository paymentMethodsRepository) {
        this.paymentMethodsFactory = paymentMethodsFactory;
        this.paymentMethodsRepository = paymentMethodsRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listPaymentMethodsToUser(@PathVariable("id") Long restaurantId,
                                                      @RequestParam("user_email") String email) {

        Set<PaymentMethod> paymentMethodsAllowed = paymentMethodsFactory.getServiceBased(restaurantId, email);
        Set<PaymentMethodsResponse> response = PaymentMethodsResponse.from(paymentMethodsAllowed);

        return ok(response);
    }
}
