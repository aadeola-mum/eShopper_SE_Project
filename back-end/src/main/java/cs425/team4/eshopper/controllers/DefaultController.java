package cs425.team4.eshopper.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Secured(value = "NONE")
public class DefaultController {
	
	//@GetMapping("/")
}
