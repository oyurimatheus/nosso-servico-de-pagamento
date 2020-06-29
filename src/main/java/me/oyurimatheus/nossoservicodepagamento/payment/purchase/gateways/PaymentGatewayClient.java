package me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.Payment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.StringJoiner;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;

@Component
class PaymentGatewayClient {

    boolean pay(URI baseUri, PaymentGatewayRequest request) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(baseUri, POST, new HttpEntity<>(request), String.class);

        return response.getStatusCode() == OK;
    }

    static class PaymentGatewayRequest {

        private BigDecimal value;
        private String creditCardNumber;
        private CreditCardFlag cardFlag;

        /**
         * @deprecated frameworks eyes only
         */
        @Deprecated
        PaymentGatewayRequest() { }

        public PaymentGatewayRequest(Payment payment) {
            Assert.notNull(payment, "payment must not be null");
            Assert.isTrue(payment.getCreditCardNumber().isPresent(), "credit card number must be present");

            this.value = payment.getTotal();
            String creditCardNumber = payment.getCreditCardNumber().get();
            this.creditCardNumber = creditCardNumber;
            this.cardFlag = CreditCardFlag.flagFrom(creditCardNumber);
        }

        public BigDecimal getValue() {
            return value;
        }

        public String getCreditCardNumber() {
            return creditCardNumber;
        }

        public CreditCardFlag getCardFlag() {
            return cardFlag;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", PaymentGatewayRequest.class.getSimpleName() + "[", "]")
                    .add("value=" + value)
                    .add("creditCardNumber='" + creditCardNumber + "'")
                    .add("cardFlag=" + cardFlag)
                    .toString();
        }
    }
}
