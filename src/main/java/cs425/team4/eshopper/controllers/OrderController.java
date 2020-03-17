package cs425.team4.eshopper.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
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
import cs425.team4.eshopper.services.OrderService;

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
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
	
}
