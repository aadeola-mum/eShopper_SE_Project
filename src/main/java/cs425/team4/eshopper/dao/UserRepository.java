package cs425.team4.eshopper.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cs425.team4.eshopper.models.Merchant;
import cs425.team4.eshopper.models.Role;
import cs425.team4.eshopper.models.User;

import java.util.Optional;

@Transactional
public interface UserRepository extends UserBaseRepository<User> {
    Optional<User> findUserByUsername(String username);
    Optional<User> deleteUserByUsername(String username);
    Boolean existsUserById(Long id);
    Boolean existsUserByUsernameAndIdIsNot(String username, Long id);
    Iterable<User> findByRoleId(long role_id);
    
}
