package cs425.team4.eshopper.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import cs425.team4.eshopper.models.Order;
import cs425.team4.eshopper.models.Product;

public interface ProductService {
	public Iterable<Product> getAll();
	public Optional<Product> get(long id);
	public Product save(Product product);
	public boolean delete(long id);
	public Page<Product> findAllbyPageAndSize(int page, int size);
	public Page<Product> search(int page, int size, String keyword);
	public Page<Product> searchByCategory(int page, int size, String category);
	
}
