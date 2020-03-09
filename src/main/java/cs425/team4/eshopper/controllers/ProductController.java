/**
 * 
 */
package cs425.team4.eshopper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs425.team4.eshopper.models.Product;
import cs425.team4.eshopper.services.ProductService;

/**
 * @author cs425 team 4
 *
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public Iterable<Product> fetchProducts(){
		return productService.getAll();
		
	}
	@GetMapping("/{productId}")
	public Product fetchProduct(@PathVariable("productId") long productId){
		return productService.get(productId);
		
	}
	
	@DeleteMapping("/{productId}")
	public boolean deleteProduct(@PathVariable("productId") long productId){
		return productService.delete(productId);
		
	}
	
	@PostMapping("/")
	public Product save(@RequestBody Product product){
		return productService.save(product);
		
	}
	
	
}
