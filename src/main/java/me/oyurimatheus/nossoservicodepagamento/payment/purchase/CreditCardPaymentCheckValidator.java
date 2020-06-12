package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CreditCardPaymentCheckValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PaymentRequest request = (PaymentRequest) target;

        if (request.isOnline() && !request.hasCreditCardNumber()) {
            errors.rejectValue("creditCardNumber", "payment.online.invalid", "Online payment method must have credit card number");
        }
    }
}
