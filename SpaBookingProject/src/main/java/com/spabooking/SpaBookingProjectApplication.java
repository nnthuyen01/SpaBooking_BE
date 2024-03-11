package com.spabooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spabooking.entities.Manager;
import com.spabooking.entities.Role;
import com.spabooking.entities.User;
import com.spabooking.repository.UserRepository;

@SpringBootApplication
public class SpaBookingProjectApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpaBookingProjectApplication.class, args);
	}
	
	public void run(String... args) {
		User adminAcount = userRepository.findByRole(Role.ADMIN);
		if(null == adminAcount) {
			User user = new Manager();
			
			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setLastname("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}

}
