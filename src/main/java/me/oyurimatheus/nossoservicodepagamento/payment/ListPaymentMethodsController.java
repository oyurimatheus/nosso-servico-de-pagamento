package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/restaurants")
class ListPaymentMethodsController {

    private final RestaurantRepository restaurantRepository;
    private final PaymentMethodCacheService paymentCache;
    private final UserRepository userRepository;
    private final Set<FraudCheck> fraudsChecking;

    public ListPaymentMethodsController(RestaurantRepository restaurantRepository,
                                        PaymentMethodCacheService paymentCache,
                                        UserRepository userRepository,
                                        Set<FraudCheck> fraudsChecking) {
        this.restaurantRepository = restaurantRepository;
        this.paymentCache = paymentCache;
        this.userRepository = userRepository;
        this.fraudsChecking = fraudsChecking;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listPaymentMethodsToUser(@PathVariable("id") Long restaurantId,
                                                      @RequestParam("user_email") String email) {

        UserFavoriteRestaurants cachedUser = paymentCache.getCachedUser(restaurantId, email);

        FindPaymentMethods searchPaymentInCache = new SearchPaymentMethodsInCache(cachedUser,
                                                                                  restaurantRepository,
                                                                                  userRepository,
                                                                                  fraudsChecking);

        Set<PaymentMethod> paymentMethods = searchPaymentInCache.findPaymentMethods();

        cachedUser.updatePaymentMethods(paymentMethods);
        paymentCache.updateCache(cachedUser);

        Set<PaymentMethodsResponse> response = PaymentMethodsResponse.from(paymentMethods);

        return ok(response);
    }

}
