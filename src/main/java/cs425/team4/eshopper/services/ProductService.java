package cs425.team4.eshopper.services;

import cs425.team4.eshopper.models.Product;

public interface ProductService {
	public Iterable<Product> getAll();
	public Product get(long id);
	public Product save(Product product);
	public boolean delete(long id);
	public Iterable<Product> search(String searchText);
	
}
