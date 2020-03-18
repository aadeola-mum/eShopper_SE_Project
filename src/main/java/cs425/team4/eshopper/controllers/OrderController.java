package cs425.team4.eshopper.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cs425.team4.eshopper.models.Order;
import cs425.team4.eshopper.models.OrderDetail;
import cs425.team4.eshopper.models.Product;
import cs425.team4.eshopper.models.User;
import cs425.team4.eshopper.services.OrderService;
import cs425.team4.eshopper.services.ProductService;
import cs425.team4.eshopper.services.UserService;
import cs425.team4.eshopper.utils.SequenceGenerator;

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	@GetMapping(value = {"/" , "/list"})
	public Page<Order> getAllOrderByPageAndSize(
			@RequestParam(name = "page" , defaultValue = "0") int page,
			@RequestParam(name = "size" , defaultValue = "10") int size){
		
		return this.orderService.findAllbyPageAndSize(page, size); 
	}
	
	public Iterable<Order> getAllOrder(){
		return this.orderService.getAll();
	}
	
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	@GetMapping(value = "/{id}")
	public Order getOrderById(@PathVariable(name = "id") long id) {
		if(id < 0) return null;
		
		Optional<Order> order = this.orderService.get(id);
		if(order.isPresent()) return order.get();
		else return null;
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping(value = "/{id}")
	public boolean deleteOrder(@RequestParam(name = "id") long id) {
		if(id < 0) return false;
		
		return this.orderService.delete(id);
	}
	
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY" , "ROLE_ADMIN"})
	@PostMapping("/")
	public Order save(@Valid @RequestBody Order order) {
		return this.orderService.save(order);
	}
	
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	@PostMapping(value = "/add-cart", consumes = "application/json")
	public ResponseEntity<Order> createNewOrder(@RequestBody HashMap<String, String> data) {
		
		long productId = Long.parseLong(data.get("productId"));
		long userId = Long.parseLong(data.get("userId"));
		int quantity = Integer.parseInt(data.get("quantity"));
		long orderId = Long.parseLong(data.get("orderId"));
		
		if(productId < 0 || quantity < 0 || userId < 0) 
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	
		User user = this.userService.findUserById(userId);
		if(user == null) {
			MultiValueMap<String , String> headers = new LinkedMultiValueMap<>();
			headers.add("error", "User Not Found");
			return new ResponseEntity<>(headers, HttpStatus.EXPECTATION_FAILED);
		}
		
		Optional<Product> product = this.productService.get(productId);
		if(!product.isPresent()) {
			MultiValueMap<String , String> headers = new LinkedMultiValueMap<>();
			headers.add("error", "Product Not Found");
			return new ResponseEntity<>(headers, HttpStatus.EXPECTATION_FAILED);
		}
		
		if(product.get().getMerchant().equals(user)) {
			MultiValueMap<String , String> headers = new LinkedMultiValueMap<>();
			headers.add("error", "This user cannot buy his own product");
			return new ResponseEntity<>(headers, HttpStatus.EXPECTATION_FAILED);
		}
		
		Order order;
		LocalDate date = LocalDate.now();
		Optional<Order> orderOpt = this.orderService.get(orderId);
		if (orderOpt.isPresent() && !orderOpt.get().getBuyer().equals(user)) {
			order = orderOpt.get();
			
		}else {
			order = new Order();
			order.setOrderID(SequenceGenerator.getInstance().getNext());
			order.setBuyer(user);
			order.setDate(date);
		}
		
		OrderDetail orderDetail = new OrderDetail(order.getOrderID(), product.get(), 0, quantity, date);
		order.addOrderDetail(orderDetail);
		order = orderService.save(order);
		
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}
	
	/*
	 * test
	 * */
	@GetMapping("/test")
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public Order get() {
		Order order = new Order();
		//order.setProduct(this.productService.get(1).get());
		return order;
	}
	
}
