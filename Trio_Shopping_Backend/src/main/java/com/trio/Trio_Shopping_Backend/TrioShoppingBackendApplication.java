package com.trio.Trio_Shopping_Backend;

import com.trio.Trio_Shopping_Backend.Controller.SmsController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrioShoppingBackendApplication {
	private  static  final Logger log = LogManager.getLogger(TrioShoppingBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TrioShoppingBackendApplication.class, args);
		log.info(" ------------------------------------------------------");
		log.info("|            STARTED TRIO_SHOPPING SERVER             |");
		log.info(" ------------------------------------------------------");
	}

}
