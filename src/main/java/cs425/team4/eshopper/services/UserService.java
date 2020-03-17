/**
 * 
 */
package cs425.team4.eshopper.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import cs425.team4.eshopper.models.Merchant;
import cs425.team4.eshopper.models.User;


/**
 * @author miu
 *
 */
public interface UserService {
	public Optional<User> findUserByUsername(String username);
    public User saveUser(User account);
    public Iterable<User> listUsers();
    public Page<User> listBuyers(int page, int size);
    public Iterable<Merchant> listMerchants();
    public Page<Merchant> listMerchant(int page, int size);
    public void deleteUser(User user);
    public User updateUser(User user);
    public User setUserPassword(String username, String newPassword);
	public User findUserById(Long userId);
	public Optional<Merchant> findMerchantById(Long userId);
}
