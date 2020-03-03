package com.lucasia.ginquiry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class GinquiryApplicationTests {

	@Value("${"+ "ginquiry.server.port" +"}")
	private int port;

	@Autowired
	Environment environment;

	@Test
	void contextLoads() {
	}


	@Test
	void findProperty() {

		System.out.println("port = " + port);


		final String property = environment.getProperty("ginquiry.server.port");

		System.out.println("property = " + property);

	}

}
