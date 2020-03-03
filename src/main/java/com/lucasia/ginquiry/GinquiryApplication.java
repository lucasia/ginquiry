package com.lucasia.ginquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@EnableFeignClients
public class GinquiryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GinquiryApplication.class, args);
	}

}
