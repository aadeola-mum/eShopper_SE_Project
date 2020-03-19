package cs425.team4.eshopper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import cs425.team4.eshopper.dao.MerchantRepository;
import cs425.team4.eshopper.dao.UserRepository;
import cs425.team4.eshopper.models.Address;
import cs425.team4.eshopper.models.Role;
import cs425.team4.eshopper.models.User;
import cs425.team4.eshopper.services.UserService;
import cs425.team4.eshopper.services.Impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestUserService {

    @Mock
    private UserRepository userRepository;

    @Mock
	private MerchantRepository merchantRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository, merchantRepository);

    private List<User> users = Arrays.asList(
    		new User("ABC", "XYZ", "test123", "abc@abc.com", new Role(1), new Address("aaa", "bbb", "ccc", "12345"), new Address("aaa", "bbb", "ccc", "12345")),
    		new User("NAC", "123", "test222", "ghj@abc.com", new Role(2), new Address("qqq", "ttt", "uuu", "12345"), new Address("das", "kkk", "ooo", "12345")),
    		new User("ABA", "YUB", "test333", "tyu@abc.com", new Role(3), new Address("rrr", "yyy", "iii", "12345"), new Address("ggf", "lll", "ppp", "12345")));

    @BeforeEach
    void setMockOutput() {
        when(userRepository.findAll()).thenReturn(users);
        
    }

    @Test
    void testGetUsers() {
        assertEquals(users, userRepository.findAll());
        verify(userRepository).findAll();
    }

    @Test
    void testGetUser() {
    	when(userRepository.findById(users.get(1).getId())).thenReturn(Optional.of(users.get(1)));
        assertEquals(users.get(1), userService.findUserById(users.get(1).getId()));
        verify(userRepository).findById(users.get(1).getId());
    }

    @Test
    void testGetUserById() {
    	when(userRepository.findById(users.get(0).getId())).thenReturn(Optional.of(users.get(0)));
        assertEquals(users.get(0), userService.findUserById(users.get(0).getId()));
    }


    @Test
    void testSaveUsers() {
        when(userRepository.save(users.get(1))).thenReturn(users.get(1));
        assertEquals(users.get(1), userService.saveUser(users.get(1)));
        verify(userRepository).save(users.get(1));
    }


}
