package cs425.team4.eshopper.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs425.team4.eshopper.dao.ProductRepository;
import cs425.team4.eshopper.models.Product;
import cs425.team4.eshopper.services.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;
	
	
	
	/**
	 * @param productRepository
	 */
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Iterable<Product> getAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product get(long id) {
		// TODO Auto-generated method stub
		return productRepository.getOne(id);
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public boolean delete(long id) {
		productRepository.deleteById(id);
		return true;
	}

	@Override
	public Iterable<Product> search(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

}
