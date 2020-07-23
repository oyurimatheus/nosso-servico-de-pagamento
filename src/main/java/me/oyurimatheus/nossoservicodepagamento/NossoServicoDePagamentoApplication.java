package me.oyurimatheus.nossoservicodepagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class NossoServicoDePagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NossoServicoDePagamentoApplication.class, args);
	}

}
