package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RestaurantRepository extends Repository<Restaurant, Long> {

    Optional<Restaurant> findById(Long id);
}
