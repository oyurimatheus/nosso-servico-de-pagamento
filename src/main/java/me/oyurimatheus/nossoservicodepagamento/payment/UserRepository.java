package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByEmail(String email);
}
