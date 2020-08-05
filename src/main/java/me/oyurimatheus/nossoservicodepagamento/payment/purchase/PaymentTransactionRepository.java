package me.oyurimatheus.nossoservicodepagamento.payment.purchase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface PaymentTransactionRepository extends Repository<PaymentTransaction, Long> {

    PaymentTransaction save(PaymentTransaction transaction);

    @Query("SELECT p FROM PaymentTransaction p WHERE p.payment.purchaseId = :purchaseId")
    Optional<PaymentTransaction> findByPurchaseId(@Param("purchaseId") String purchaseId);

    Optional<PaymentTransaction> findById(Long id);
}
