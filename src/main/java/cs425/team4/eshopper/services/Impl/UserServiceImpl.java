package cs425.team4.eshopper.services.Impl;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs425.team4.eshopper.dao.MerchantRepository;
import cs425.team4.eshopper.dao.UserRepository;
import cs425.team4.eshopper.models.Merchant;
import cs425.team4.eshopper.models.Role;
import cs425.team4.eshopper.models.User;
import cs425.team4.eshopper.services.UserService;
import cs425.team4.eshopper.utils.Constants;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private MerchantRepository merchantRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, MerchantRepository merchantRepository) {
		this.userRepository = userRepository;
		this.merchantRepository = merchantRepository;
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
	public Iterable<Merchant> listMerchants() {
		return merchantRepository.findAll();
	}

	@Override
	public Optional<Merchant> findMerchantById(Long userId) {
		return merchantRepository.findById(userId);
	}

	@Override
	public Page<User> listBuyers(int page, int size) {
		if(page < 0) page = 0;
		if(size <= 0) size = 10;
		Pageable pageable = PageRequest.of(page , size, Sort.by("firstName"));
		return userRepository.findAll(pageable);
	}

	@Override
	public Page<Merchant> listMerchant(int page, int size) {
		if(page < 0) page = 0;
		if(size <= 0) size = 10;
		Pageable pageable = PageRequest.of(page , size, Sort.by("firstName"));
		return merchantRepository.findAll(pageable);
	}
}
