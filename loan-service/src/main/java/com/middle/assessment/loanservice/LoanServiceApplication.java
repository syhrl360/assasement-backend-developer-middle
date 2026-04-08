package com.middle.assessment.loanservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LoanServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(LoanServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoanServiceApplication.class, args);
		log.info("\n=======================================================\n\t" +
				"Application running\n\t" +
				"Swagger Documentation: http://localhost:8087/swagger-ui/index.html\n\t" +
				"==========================================================");
	}

}
