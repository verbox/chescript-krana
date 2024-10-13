package org.krana;

import org.chescript.krana.common.model.CardPhenotype;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KranaServerApplication {

	public static void main(String[] args) {
		CardPhenotype card = new CardPhenotype();
		SpringApplication.run(KranaServerApplication.class, args);
	}

}
