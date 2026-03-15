package com.middle.assessment.accountservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(AccountServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
		log.info("\n=======================================================\n\t" +
				"Application running\n\t" +
				"Swagger Documentation: http://localhost:8089/swagger-ui/index.html\n\t" +
				"==========================================================");
	}

}
