/**
 *
 */
package cs425.team4.eshopper.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import cs425.team4.eshopper.exceptions.AdminsCannotDeleteThemselvesException;
import cs425.team4.eshopper.exceptions.ItemNotFoundException;
import cs425.team4.eshopper.factory.UserFactory;
import cs425.team4.eshopper.models.Merchant;
import cs425.team4.eshopper.models.User;
import cs425.team4.eshopper.services.UserService;
import cs425.team4.eshopper.services.Impl.UserDetailsImpl;
import cs425.team4.eshopper.services.Impl.UserDetailsServiceImpl;
import cs425.team4.eshopper.utils.JwtUtil;

/**
 * @author cs425 team 6
 *
 */

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/api/v1/users")
public class UserController {

	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

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
	    @Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) throws Exception {
	    		String username = payload.get("username");
	    		try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, payload.get("password")));
			} catch (AuthenticationException e) {
				throw new Exception("Invalid username/password");
			}

	    		final UserDetailsImpl userDetail = (UserDetailsImpl) userDetailService.loadUserByUsername(username);
	    		final String jwtToken =  jwtUtil.generateToken(userDetail);
	    		//User loggedInUser = userDetail.getUser();
	    		Map<String, Object> response = new HashMap<>();
	    		//response.put("user", loggedInUser);
	    		response.put("token", jwtToken);
	    		response.put("uid", userDetail.getUser().getId());
	    		response.put("type", "Bearer");
	    		response.put("role", userDetail.getUser().getRole().getType());
			    response.put("name", userDetail.getUser().getFirstName());
			    response.put("account", userDetail.getUser().getUsername());
	        return ResponseEntity.ok(response);
	    }

	    @Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	    @PostMapping("/register")
	    public User createUser(@RequestBody @Valid User user) {
	    	UserFactory userFactory = UserFactory.getInstance();
	        return userService.saveUser(userFactory.createUser(user,"buyer"));
	    	//return userService.saveUser(user);
	    }
	    
	    @Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	    @PostMapping("/merchants/register")
	    public User createMerchant(@RequestBody @Valid Merchant user) {
	    	UserFactory userFactory = UserFactory.getInstance();
	        return userService.saveUser(userFactory.createUser(user, "merchant"));
	    	//return userService.saveUser(user);
	    }
	    
	    @Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	    @PutMapping("/update")
	    public User updateUser(@RequestBody @Valid User user) {
	        return userService.saveUser(user); 
	    }
	    
	    @Secured(value = {"ROLE_ADMIN"})
	    @PostMapping("/admin/create")
	    public ResponseEntity<Object> newAdminUser(@RequestBody @Valid User user) {
	        userService.saveUser(user);

	        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(user.getUsername())
	                .toUri();

	        return ResponseEntity.created(location).build();
	    }

	    @Secured(value = {"ROLE_ADMIN"})
	    @GetMapping("/buyers")
	    public Iterable<User> allBuyers() {
	        return userService.listBuyers();
	    }

	    @Secured(value = {"ROLE_ADMIN"})
	    @GetMapping("/merchants")
	    public Iterable<User> allMerchants() {
	        return userService.listMerchants();
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
	        oldUser.setRole(newUser.getRole());
	        oldUser.setShippingAddress(newUser.getShippingAddress());
	        oldUser.setEnabled(newUser.isEnabled());
	        oldUser.setPassword(newUser.getPassword());
	        oldUser.setFirstName(newUser.getFirstName());
	        oldUser.setLastName(newUser.getLastName());
	        return userService.updateUser(oldUser);
	    }
}
