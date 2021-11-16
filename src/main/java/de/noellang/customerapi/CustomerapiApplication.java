package de.noellang.customerapi;

import de.noellang.customerapi.entity.User;
import de.noellang.customerapi.repository.UserRepository;
import de.noellang.customerapi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CustomerapiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public static void main(String[] args) {
		SpringApplication.run(CustomerapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail("noel@noel.de");
		user.setPassword(passwordEncoder.encode("1234"));

		User saved = userRepository.save(user);

		System.out.println(jwtTokenProvider.generateToken(saved.getEmail()));
	}
}
