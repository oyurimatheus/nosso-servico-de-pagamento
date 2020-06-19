package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import org.springframework.data.repository.Repository;

interface PaymentRepository extends Repository<Payment, Long> {

    Payment save(Payment payment);
}
