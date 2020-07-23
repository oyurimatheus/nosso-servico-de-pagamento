package me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways;

import me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways.PaymentGatewayClient.PaymentGatewayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

@RestController
@RequestMapping("/gateways/pay")
class MockGatewaysController {

    private static final Logger LOG = LoggerFactory.getLogger(MockGatewaysController.class);

    @PostMapping
    public ResponseEntity<?> pay(@RequestBody PaymentGatewayRequest request) {

        LOG.info("[PAYMENT] [RECEIVED] Payment received {}", request);

        return unprocessableEntity().build();
    }
}
