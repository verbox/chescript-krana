package org.krana;

import org.chescript.krana.common.model.Card;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KranaServerApplication {

	public static void main(String[] args) {
		Card card = new Card();
		SpringApplication.run(KranaServerApplication.class, args);
	}

}
