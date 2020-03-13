package cs425.team4.eshopper.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import cs425.team4.eshopper.models.OrderDetail;

public interface OrderDetailService {
	
	/*public Double getPrice(OrderDetail orderDetail);
	public Double getTax(OrderDetail orderDetail);
	public Double getTotalPrice(OrderDetail orderDetail);*/
	
	public Iterable<OrderDetail> getAll();
	public Page<OrderDetail> findAllbyPageAndSize(int page, int size);
	public Optional<OrderDetail> get(Long id);
	public OrderDetail save(OrderDetail orderDetail);
	public boolean delete(Long id);
	
}