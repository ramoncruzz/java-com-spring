package com.ramon.teste;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class VitoriaSrvRestfullApplication {

	

	public static void main(String[] args) {
	 ConfigurableApplicationContext context=SpringApplication.run(VitoriaSrvRestfullApplication.class, args);
		
	}
}
