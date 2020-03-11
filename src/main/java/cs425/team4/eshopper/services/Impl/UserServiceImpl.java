package cs425.team4.eshopper.services.Impl;



import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs425.team4.eshopper.dao.UserRepository;
import cs425.team4.eshopper.models.Merchant;
import cs425.team4.eshopper.models.Role;
import cs425.team4.eshopper.models.User;
import cs425.team4.eshopper.services.UserService;
import cs425.team4.eshopper.utils.Constants;



@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Transactional(readOnly = true)
	public void validateUniqueUserFields(@Valid User account) {
		if (userRepository.existsUserByUsernameAndIdIsNot(account.getUsername(), account.getId())) {
			throw new ValidationException("Username " + account.getUsername() + " is already taken.");
		}
		
	}

	@Transactional
	@Override
	public User saveUser(@Valid User account) {
		validateUniqueUserFields(account);
		return userRepository.save(account);
	}

	@Transactional(readOnly = true)
	public Iterable<User> listUsers() {
		return userRepository.findAll();
	}

	@Transactional
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Transactional
	public User updateUser(@Valid User newUser) {
		validateUniqueUserFields(newUser);
		return userRepository.save(newUser);
	}

	@Transactional
	public User setUserPassword(String username, String newPassword) {
		return userRepository.findUserByUsername(username).map((user) -> {
			user.setPassword(newPassword);
			return userRepository.save(user);
		}).orElseThrow(() -> new UsernameNotFoundException("Invalid username."));
	}

	@Transactional(readOnly = true)
	@Override
	public User findUserById(Long userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public Iterable<User> listBuyers() {
		return userRepository.findByRoleId(1);
	}

	@Override
	public Iterable<User> listMerchants() {
		return userRepository.findByRoleId(2);
	}
}
