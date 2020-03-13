package cs425.team4.eshopper.dao;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs425.team4.eshopper.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	public Optional<Order> findById(Long id);
	
	public Page<Order> findAll(Pageable pageable);
	
}
