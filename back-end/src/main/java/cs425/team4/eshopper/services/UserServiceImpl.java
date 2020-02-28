package cs425.team4.eshopper.services;



import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs425.team4.eshopper.dao.UserRepository;
import cs425.team4.eshopper.models.User;



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
	public User findUserById(UUID userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public Iterable<User> listNonMerchantUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> listMerchantUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
