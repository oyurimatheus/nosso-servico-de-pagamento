package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;
import java.util.Set;

interface RestaurantRepository extends Repository<Restaurant, Long> {

    Optional<Restaurant> findById(Long id);
}
