package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.RestaurantRepository;
import me.oyurimatheus.nossoservicodepagamento.payment.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/purchase")
class PaymentController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentProcessor paymentProcessor;
    private final PaymentMethodChosenValidator paymentMethodChosenValidator;

    PaymentController(RestaurantRepository restaurantRepository,
                      UserRepository userRepository,
                      PaymentRepository paymentRepository,
                      PaymentProcessor paymentProcessor,
                      PaymentMethodChosenValidator paymentMethodChosenValidator) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.paymentProcessor = paymentProcessor;
        this.paymentMethodChosenValidator = paymentMethodChosenValidator;
    }

    @PostMapping
    ResponseEntity<?> pay(@RequestBody @Valid PaymentRequest paymentRequest) {

        var payment = paymentRequest.toPayment(restaurantRepository::findById, userRepository::findByEmail);
        paymentRepository.save(payment);

        PaymentTransaction transactional = paymentProcessor.process(payment);

        HashMap<String, Object> response = new HashMap<>();
        response.put("status", transactional.getStatus());
        return ok(response);

    }

    @InitBinder(value = { "paymentRequest" })
    void initBinder(WebDataBinder binder) {

        binder.addValidators(paymentMethodChosenValidator,
                             new CreditCardPaymentCheckValidator());
    }


}