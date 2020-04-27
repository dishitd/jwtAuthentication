package com.helloworld.jwt;

import com.helloworld.jwt.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JwtApplication  {

	private static final Logger logger = LoggerFactory.getLogger(JwtApplication.class);

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
