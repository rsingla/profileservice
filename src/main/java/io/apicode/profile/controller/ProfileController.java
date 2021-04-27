package io.apicode.profile.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.apicode.profile.model.Profile;
import io.apicode.profile.service.UserService;

@RestController
public class ProfileController {

	@Autowired
	UserService userService;

	@GetMapping(path = "profile/{id}", produces = { "application/json" })
	public Profile checkHealth(@PathVariable String id) throws InterruptedException, ExecutionException {
		Profile profile = userService.getProfileDetails(id);
		return profile;
	}

}
