package cs425.team4.eshopper.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import cs425.team4.eshopper.models.Order;

public interface OrderService {

	/*public Float getTax(Order order);
	public Float getTotalPrice(Order order);*/
	
	public Iterable<Order> getAll();
	public Page<Order> findAllbyPageAndSize(int page, int size);
	public Optional<Order> get(Long id);
	public Order save(Order order);
	public boolean delete(Long id);
	
	public Optional<Order> getCurrentOrderOfUser(long userId , String username);
	public Optional<Order> getCurrentOrderOfUser(long userId);
	
}
