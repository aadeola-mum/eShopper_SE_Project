package cs425.team4.eshopper.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs425.team4.eshopper.dao.UserRepository;
import cs425.team4.eshopper.models.User;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
	
	private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        return new UserDetailsImpl(user.get());
    }
}
