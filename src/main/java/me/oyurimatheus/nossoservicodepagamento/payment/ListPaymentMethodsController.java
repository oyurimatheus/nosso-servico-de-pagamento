package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/restaurants")
class ListPaymentMethodsController {

    private final PaymentMethodCacheService paymentCache;
    private final SearchPaymentMethodsInCache searchPaymentInCache;

    public ListPaymentMethodsController(PaymentMethodCacheService paymentCache,
                                        SearchPaymentMethodsInCache searchPaymentInCache) {
        this.paymentCache = paymentCache;
        this.searchPaymentInCache = searchPaymentInCache;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listPaymentMethodsToUser(@PathVariable("id") Long restaurantId,
                                                      @RequestParam("user_email") String email) {

        UserFavoriteRestaurants cachedUser = paymentCache.getCachedUser(restaurantId, email);

        Set<PaymentMethod> paymentMethods = searchPaymentInCache.findPaymentMethods(cachedUser);

        Set<PaymentMethodsResponse> response = PaymentMethodsResponse.from(paymentMethods);

        return ok(response);
    }

}
