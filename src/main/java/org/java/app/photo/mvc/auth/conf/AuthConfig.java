package org.java.app.photo.mvc.auth.conf;


import org.java.app.photo.mvc.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

	
	@Bean
		SecurityFilterChain filterChain(HttpSecurity http)
			throws Exception {
				http.csrf().disable().authorizeHttpRequests()
					.requestMatchers("/api/v1.0/").permitAll()
					.requestMatchers("/pictures/create").hasAuthority("ADMIN")
					.requestMatchers("/pictures/update/").hasAuthority("ADMIN")
					.requestMatchers("/pictures/delete/").hasAuthority("ADMIN")
					.requestMatchers("/pictures/test\\d").hasAuthority("ADMIN")
					.requestMatchers("/**").permitAll()
					.and().formLogin().defaultSuccessUrl("/pictures")
					.and().logout();
		return http.build();
	}
	
	@Bean
	UserService userDetailService() {
		return new UserService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
}
