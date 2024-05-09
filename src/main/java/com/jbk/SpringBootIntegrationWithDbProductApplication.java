package com.jbk;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootIntegrationWithDbProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntegrationWithDbProductApplication.class, args);
		System.out.println(1111);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
