package me.oyurimatheus.nossoservicodepagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NossoServicoDePagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NossoServicoDePagamentoApplication.class, args);
	}

}
