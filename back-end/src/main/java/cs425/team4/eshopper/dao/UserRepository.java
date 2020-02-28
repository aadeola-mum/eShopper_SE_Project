package cs425.team4.eshopper.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import cs425.team4.eshopper.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsername(String username);
    Optional<User> deleteUserByUsername(String username);
    Boolean existsUserById(UUID id);
    Boolean existsUserByUsernameAndIdIsNot(String username, UUID id);
    
}
