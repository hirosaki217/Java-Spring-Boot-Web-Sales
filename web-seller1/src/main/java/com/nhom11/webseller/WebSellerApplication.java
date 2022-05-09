package com.nhom11.webseller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.nhom11.webseller.config.StorageProperties;
import com.nhom11.webseller.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class WebSellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSellerApplication.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService service) {
		return (args->{
			service.init();
		});
	}
}
