package me.oyurimatheus.nossoservicodepagamento.payment;

import java.util.Set;

interface FindPaymentMethods {

    Set<PaymentMethod> findPaymentMethods();
}
