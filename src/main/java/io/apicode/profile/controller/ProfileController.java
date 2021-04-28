package io.apicode.profile.controller;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.apicode.profile.model.Profile;
import io.apicode.profile.service.UserService;

@RestController
public class ProfileController {

	@Autowired
	UserService userService;

	@Autowired
	Validator validator;

	Logger log = Logger.getLogger("Profile Controller");

	@GetMapping(path = "profile/{id}", produces = { "application/json" })
	public Profile getProfileDetails(@PathVariable String id) throws InterruptedException, ExecutionException {
		Profile profile = userService.getProfileDetails(id);
		return profile;
	}

	@PostMapping(path = "profile", produces = { "application/json" })
	public @ResponseBody Profile createProfile(@Valid @RequestBody Profile profile)
			throws InterruptedException, ExecutionException {

		
		Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

		if (!violations.isEmpty()) {
			for (ConstraintViolation<Profile> violation : violations) {
				log.info("" + violation.getConstraintDescriptor());
				log.info(violation.getMessage());
			}
		} else {

			Profile profileResponse = userService.saveProfileDetails(profile);
			return profileResponse;
		}

		return null;
	}

}
