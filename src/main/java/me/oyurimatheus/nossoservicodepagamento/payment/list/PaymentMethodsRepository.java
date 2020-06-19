package me.oyurimatheus.nossoservicodepagamento.payment.list;

import me.oyurimatheus.nossoservicodepagamento.payment.list.UserFavoriteRestaurants;
import org.springframework.data.repository.CrudRepository;

interface PaymentMethodsRepository extends CrudRepository<UserFavoriteRestaurants, String> {
}
