package com.example.fhir_etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@RestController
public class FhirEtlApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(FhirEtlApplication.class, args);
		// Camel a = new Camel();
		// a.run1();
		// System.exit(0);
	}

	@RequestMapping(value = "/")
	public String hello() {
	   return "Hello World from Tomcat";
	}
}
