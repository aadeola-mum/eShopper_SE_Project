/**
 * 
 */
package cs425.team4.eshopper.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
	
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	@GetMapping("/list")
	public Iterable<Product> fetchProducts(){
		return productService.getAll();
		
	}
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	@GetMapping("/{productId}")
	public Product fetchProduct(@PathVariable("productId") long productId){
		if(productService.get(productId).isPresent())
			return productService.get(productId).get();
		else
			return null;
		
	}
	
	@Secured(value = {"ROLE_MERCHANT", "ROLE_ADMIN"})
	@DeleteMapping("/{productId}")
	public boolean deleteProduct(@PathVariable("productId") long productId){
		return productService.delete(productId);
	}
	
	@Secured(value = {"ROLE_MERCHANT", "ROLE_ADMIN"})
	@PostMapping("/")
	public Product save(@RequestBody Product product){
		return productService.save(product);
		
	}
	
	
}
