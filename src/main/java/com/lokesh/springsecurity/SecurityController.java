package com.lokesh.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@GetMapping("/check")
	public String healthCheck() {
	  return "Hey Spring Security!";
	}
	
	@GetMapping("/bye")
	public String failureCheck() {
	  return "Hey Spring Security is not working!";
	}

}
