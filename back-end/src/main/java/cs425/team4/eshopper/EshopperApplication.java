package cs425.team4.eshopper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@SpringBootApplication
public class EshopperApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopperApplication.class, args);
	}
	
	

}
