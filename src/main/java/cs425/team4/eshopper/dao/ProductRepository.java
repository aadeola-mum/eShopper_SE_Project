package cs425.team4.eshopper.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs425.team4.eshopper.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	Optional<Product> findById(long id);
}
