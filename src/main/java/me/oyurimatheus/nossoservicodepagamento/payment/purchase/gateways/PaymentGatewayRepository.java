package me.oyurimatheus.nossoservicodepagamento.payment.purchase.gateways;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface PaymentGatewayRepository extends Repository<PaymentGateway, Long> {

    List<PaymentGateway> findAll();
}
