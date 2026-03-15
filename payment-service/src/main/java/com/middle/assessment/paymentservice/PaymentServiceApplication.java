package com.middle.assessment.paymentservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(PaymentServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
		log.info("\n=======================================================\n\t" +
				"Application running\n\t" +
				"Swagger Documentation: http://localhost:8088/swagger-ui/index.html\n\t" +
				"==========================================================");
	}

}
