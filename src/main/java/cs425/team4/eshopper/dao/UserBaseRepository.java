package cs425.team4.eshopper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import cs425.team4.eshopper.models.User;

@Repository
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Long> {

}