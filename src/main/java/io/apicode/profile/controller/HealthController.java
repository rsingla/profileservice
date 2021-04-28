package io.apicode.profile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	
	@GetMapping(path = "profile/health", produces = {"application/json"})
	public String checkHealth() {
		String service = "Service Profile Service";
		return service;
	}

	
	@GetMapping(path = "/", produces = {"application/json"})
	public String serviceCheck() {
		String service = "Service Profile Service Working correctly";
		return service;
	}
}
