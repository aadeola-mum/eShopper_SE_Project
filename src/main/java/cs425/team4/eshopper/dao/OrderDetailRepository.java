package cs425.team4.eshopper.dao;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs425.team4.eshopper.models.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

	public Optional<OrderDetail> findById(Long id);	
	public Page<OrderDetail> findAll(Pageable pageable);
}
