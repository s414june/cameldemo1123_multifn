package com.example.demosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSqlApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoSqlApplication.class, args);
		Camel a = new Camel();
		a.run2();
		System.exit(0);
	}

}
