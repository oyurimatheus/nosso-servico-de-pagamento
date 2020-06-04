package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.data.repository.CrudRepository;

interface PaymentMethodsRepository extends CrudRepository<UserFavoriteRestaurants, String> {
}
