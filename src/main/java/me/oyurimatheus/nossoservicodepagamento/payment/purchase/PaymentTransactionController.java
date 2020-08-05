package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import me.oyurimatheus.nossoservicodepagamento.payment.User;
import me.oyurimatheus.nossoservicodepagamento.payment.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/users/{user_id}/transaction/{transaction_id}")
class PaymentTransactionController {

    private final UserRepository userRepository;
    private final PaymentTransactionRepository paymentTransactionRepository;

    PaymentTransactionController(UserRepository userRepository,
                                 PaymentTransactionRepository paymentTransactionRepository) {
        this.userRepository = userRepository;
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    @GetMapping
    public ResponseEntity<?> getTransaction(@PathVariable("user_id") Long userId,
                                            @PathVariable("transaction_id") Long transactionId) {

        User user = userRepository.findById(userId).get();
        PaymentTransaction transaction = paymentTransactionRepository.findById(transactionId).get();

        if (transaction.generatedBy(user)) {
            return ok(PaymentTransactionResponse.from(transaction));
        }

        return unprocessableEntity().build();
    }
}
