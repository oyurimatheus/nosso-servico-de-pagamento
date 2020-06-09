package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/restaurants")
class ListPaymentMethodsController {

    private final PaymentMethodCacheService paymentCacheService;
    private final SearchPaymentInDatabase searchPaymentInDatabase;

    public ListPaymentMethodsController(PaymentMethodCacheService paymentCache,
                                        SearchPaymentInDatabase searchPaymentInDatabase) {
        this.paymentCacheService = paymentCache;
        this.searchPaymentInDatabase = searchPaymentInDatabase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listPaymentMethodsToUser(@PathVariable("id") Long restaurantId,
                                                      @RequestParam("user_email") String email) {

        UserFavoriteRestaurants cachedUser = paymentCacheService.getCachedUser(restaurantId, email);

        if (cachedUser.incrementAndGet() >= 10) {
            Set<PaymentMethod> paymentMethods = cachedUser.getPaymentMethods();
            Set<PaymentMethodsResponse> response = PaymentMethodsResponse.from(paymentMethods);

            return ok(response);
        }

        Set<PaymentMethod> paymentMethods = searchPaymentInDatabase.findPaymentMethods(cachedUser);

        cachedUser.updatePaymentMethods(paymentMethods);
        paymentCacheService.updateCache(cachedUser);

        Set<PaymentMethodsResponse> response = PaymentMethodsResponse.from(paymentMethods);

        return ok(response);
    }

}
