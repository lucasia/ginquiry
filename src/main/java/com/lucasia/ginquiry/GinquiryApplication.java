package com.lucasia.ginquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.lucasia.ginquiry"})
public class GinquiryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GinquiryApplication.class, args);
	}

}
