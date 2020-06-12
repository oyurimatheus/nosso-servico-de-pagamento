package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import org.springframework.data.repository.Repository;

interface PaymentTransactionRepository extends Repository<PaymentTransaction, Long> {

    PaymentTransaction save(PaymentTransaction transaction);
}
