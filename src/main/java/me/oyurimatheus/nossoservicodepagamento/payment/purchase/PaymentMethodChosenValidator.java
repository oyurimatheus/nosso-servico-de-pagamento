package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.*;
import me.oyurimatheus.nossoservicodepagamento.payment.list.FraudCheck;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
import java.util.Set;

@Component
class PaymentMethodChosenValidator implements Validator {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private Set<FraudCheck> fraudChecking;

    public PaymentMethodChosenValidator(RestaurantRepository restaurantRepository,
                                        UserRepository userRepository,
                                        Set<FraudCheck> fraudChecking) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.fraudChecking = fraudChecking;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PaymentRequest request = (PaymentRequest) target;
        String userEmail = request.getUserEmail();
        Long restaurantId = request.getRestaurantId();

        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        User user = userRepository.findByEmail(userEmail).get();

        Set<PaymentMethod> paymentMethods = user.paymentMethodsTo(restaurant, fraudChecking);
        if (!paymentMethods.contains(request.getPaymentMethod())) {
            errors.rejectValue("paymentMethod", "payment.paymentMethod.invalid", "This payment method is not available");
        }
    }
}
