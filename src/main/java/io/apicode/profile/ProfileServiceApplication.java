package io.apicode.profile;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

	@Bean
	public Validator validator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Validator validator = factory.getValidator();
		return validator;
	}

}
