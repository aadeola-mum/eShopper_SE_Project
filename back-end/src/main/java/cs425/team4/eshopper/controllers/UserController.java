/**
 * 
 */
package cs425.team4.eshopper.controllers;

import java.net.URI;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import cs425.team4.eshopper.exceptions.AdminsCannotDeleteThemselvesException;
import cs425.team4.eshopper.exceptions.ItemNotFoundException;
import cs425.team4.eshopper.models.User;
import cs425.team4.eshopper.services.UserService;

/**
 * @author cs425 team 4
 *
 */

@RestController("/api/v1/users")
public class UserController {
	 private UserService userService;

	    @Autowired
	    public UserController(UserService userService) {
	        this.userService = userService;
	    }
	    
	    @Secured({"ROLE_BUYER", "ROLE_MERCHANT", "ROLE_ADMIN"})
	    @PostMapping("/changePassword")
	    public String changePassword(@RequestBody Map<String, String> payload) {
	        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

	        this.userService.setUserPassword(currentUsername, payload.get("password"));
	        return payload.get("password");
	    }
	    @Secured({"ROLE_BUYER", "ROLE_MERCHANT", "ROLE_ADMIN"})
	    @PostMapping("/login")
	    public String login(@RequestBody Map<String, String> payload) {
	        return "";
	    }

	    @Secured(value = {"ROLE_ADMIN"})
	    @PostMapping
	    public ResponseEntity<Object> newUser(@RequestBody @Valid User user) {
	        userService.saveUser(user);

	        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(user.getUsername())
	                .toUri();

	        return ResponseEntity.created(location).build();
	    }

	    @Secured(value = {"ROLE_ADMIN"})
	    @GetMapping("/buyers")
	    public Iterable<User> allUsers() {
	        return userService.listNonMerchantUsers();
	    }
	    
	    @Secured(value = {"ROLE_ADMIN"})
	    @GetMapping("/merchants")
	    public Iterable<User> allMerchants() {
	        return userService.listMerchantUsers();
	    }

	    @Secured(value = {"ROLE_ADMIN","ROLE_MERCHANT","ROLE_BUYER"})
	    @GetMapping("/{username}")
	    public User one(@PathVariable String username) {
	        return userService.findUserByUsername(username)
	        		.orElseThrow(() -> new ItemNotFoundException(username, User.class));
	    }

	    @Secured(value = {"ROLE_ADMIN"})
	    @DeleteMapping("/{username}")
	    public User deleteUser(@PathVariable String username) {
	        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
	        if(username.equals(currentUsername))
	            throw new AdminsCannotDeleteThemselvesException(username);
	        User user = userService.findUserByUsername(username).map( u -> {
	            userService.deleteUser(u);
	            return u;
	        }).orElse(null);

	        return user;
	    }

	    @Secured(value = {"ROLE_ADMIN"})
	    @PutMapping("/{username}")
	    public User replaceUser(@RequestBody @Valid User newUser, @PathVariable String username) {
	        User oldUser = userService.findUserByUsername(username).orElse(newUser);
	        oldUser.setBillingAddress(newUser.getBillingAddress());
	        oldUser.setRoles(newUser.getRoles());
	        oldUser.setShippingAddress(newUser.getShippingAddress());
	        oldUser.setEnabled(newUser.isEnabled());
	        oldUser.setPassword(newUser.getPassword());
	        oldUser.setFirstName(newUser.getFirstName());
	        oldUser.setLastName(newUser.getLastName());
	        return userService.updateUser(oldUser);
	    }
}
