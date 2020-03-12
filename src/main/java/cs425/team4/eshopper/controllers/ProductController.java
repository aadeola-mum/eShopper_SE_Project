/**
 * 
 */
package cs425.team4.eshopper.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cs425.team4.eshopper.exceptions.ItemNotFoundException;
import cs425.team4.eshopper.models.Merchant;
import cs425.team4.eshopper.models.Product;
import cs425.team4.eshopper.models.User;
import cs425.team4.eshopper.services.ProductService;
import cs425.team4.eshopper.services.UserService;

/**
 * @author cs425 team 4
 *
 */
@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
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
	@PostMapping("/{merchantId}")
	public Product save(@Valid @RequestBody Product product, @PathVariable long merchantId) throws Exception{
		//System.err.println("Merchant found: ");
		Merchant m = userService.findMerchantById(merchantId).get();
		if(m == null) {
			throw new Exception("No registered merchant found with the parameter sent");
		}
		//System.err.println("Merchant found: "+m);
		product.setMerchant(m);
		return productService.save(product);
		
	}
	
	
}
