package com.middle.assessment.accountservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AccountServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(AccountServiceApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext application =  SpringApplication.run(AccountServiceApplication.class, args);
		Environment env = application.getEnvironment();
		log.info("\n=======================================================\n\t" +
				"Application running\n\t" +
				"Swagger Documentation: http://localhost:"+ env.getProperty("server.port") + "/swagger-ui/index.html\n\t" +
				"==========================================================");
	}

}
