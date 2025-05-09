package br.com.collector.game.box;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CollectorGameBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectorGameBoxApplication.class, args);
	}

}
