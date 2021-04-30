package io.apicode.profile.controller;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.apicode.profile.model.APIException;
import io.apicode.profile.model.Profile;
import io.apicode.profile.model.Util;
import io.apicode.profile.service.UserService;

@RestController
public class ProfileController {

	@Autowired
	UserService userService;

	@Autowired
	Validator validator;

	Logger log = Logger.getLogger("Profile Controller");

	@GetMapping(path = "profile/{id}", produces = { "application/json" })
	public @ResponseBody Profile getProfileDetails(@PathVariable String id) throws InterruptedException, ExecutionException {
		Profile profile = userService.getProfileDetails(id);
		return profile;
	}

	@PostMapping(path = "profile", produces = { "application/json" })
	public @ResponseBody Profile createProfile(@Valid @RequestBody Profile profile)
			throws InterruptedException, ExecutionException, APIException {

		// Validate Email

		String id = userService.getByEmail(profile.getEmail());

		if (id != null) {
			throw new APIException("User already Exists " + id);
		}

		Profile profileResponse = userService.saveProfileDetails(profile);
		return profileResponse;

	}

	@PutMapping(path = "profile/{id}", produces = { "application/json" })
	public @ResponseBody Profile updateProfile(@Valid @RequestBody Profile profile, @PathVariable String id)
			throws InterruptedException, ExecutionException, APIException {

		// Existing DB Profile
		Profile profileDB = userService.getProfileDetails(id);

		if (profileDB == null) {
			throw new APIException("User cannot be updated as it doesnt exists, Please insert");
		}

		String idWithEmail = userService.getByEmail(profile.getEmail());

		if (!idWithEmail.equalsIgnoreCase(id)) {
			throw new APIException("Profile already exist and doesnt match the update info  " + id);
		}

		Profile profileResponse = userService.updateProfileDetails(profile, id);
		return profileResponse;

	}

	@PatchMapping(path = "profile/{id}", produces = { "application/json" })
	public @ResponseBody Profile upsertProfile(@RequestBody Profile profile, @PathVariable String id)
			throws InterruptedException, ExecutionException, APIException {

		// Existing DB Profile
		Profile profileDB = userService.getProfileDetails(id);

		if (profileDB == null) {
			throw new APIException("User cannot be updated as it doesnt exists, Please insert");
		}

		if (profile.getEmail() != null) {
			String idWithEmail = userService.getByEmail(profile.getEmail());

			if (!idWithEmail.equalsIgnoreCase(id)) {
				throw new APIException("Profile already exist and doesnt match the update info  " + id);
			}
		}

		// Merge the Input Profile with DB Profile
		profile = Util.merge(profile, profileDB);

		Profile profileResponse = userService.updateProfileDetails(profile, id);
		return profileResponse;

	}

	@DeleteMapping(path = "profile/{id}", produces = { "application/json" })
	public @ResponseBody String deleteProfileById(@PathVariable String id) throws InterruptedException, ExecutionException {
		String info = userService.deleteProfile(id);
		return info;
	}

}
