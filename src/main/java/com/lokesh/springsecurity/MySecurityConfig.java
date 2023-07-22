package com.lokesh.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class MySecurityConfig {

	// Step 2:
	// Creating a custom UserDetailService and Inmemory and user as below and
	// return the customized userDetailService

	// TODO
	// we are commenting this to check the custom auth provider,
	// id custom AuthentcationProvider is not provided
	// then this UserDetailsService is mandatory

//	@Bean
//	UserDetailsService userDetailsService() {
//		// as we are storing the creds in inmemory we are creating 
//		//this InMemoryUserDetailsManager
//		InMemoryUserDetailsManager inMemoryuserDetailService = new InMemoryUserDetailsManager();
//		//and creating this custom user and minimum one authorities is required for a user
//		UserDetails user = User.withUsername("Lokesh")
//				.password(bCryptPasswordEncoder().encode("Pathapati"))
//				.authorities("read").build();
//		//creating the user with the above user creds
//		inMemoryuserDetailService.createUser(user);
//		return inMemoryuserDetailService;
//	}

	// Step 3:
	// Use the bcrpyt encoder to encrypt the password and use it
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();

	}

	// Step:1 creating SecurityFilterChain bean
	// we are exposing a bean SecurityFilterChain to customize/ our security which
	// accepts
	// a HttpSecurity object, as soon as the application sees a SecurityFilterChain
	// it
	// overrides the default and expects the what we return

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		// as we are using the basic security, we can check from postman
		// using httpSecurity.httpBasic();
		httpSecurity.httpBasic();
		// we can also use form based login with the below method
		// httpSecurity.formLogin();
		// we are authorizing all http requests which are coming in below line
		httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
		// we can authorise specific end point as below and deny for all other end
		// points
		// httpSecurity.authorizeHttpRequests().regexMatchers("/check").authenticated().anyRequest().denyAll();
		// we are adding a custom filter and specify the order at which
		// the filter should go in uisng the addFilterBefore/After methods
		// which takes two params (our custom filter, the one before/after)
		httpSecurity.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);
		return httpSecurity.build();
	}

}
