package cs425.team4.eshopper.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This field is required")
    @Column(nullable = false)
    private String orderID;

    @NotNull
    @OneToOne
    private User buyer;

    @NotNull
    @Column(nullable = false)
    @PastOrPresent
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private Boolean paid;

    @NotNull
    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(String orderID, User buyer, LocalDate date, Boolean paid, List<OrderDetail> orderDetails ) {
        this.orderID = orderID;
        this.buyer = buyer;
        this.date = date;
        this.paid = paid;
        this.orderDetails = orderDetails;
    }

    public Order(String orderID, User buyer, LocalDate date, Boolean paid) {
        this.orderID = orderID;
        this.buyer = buyer;
        this.date = date;
        this.paid = paid;
        this.orderDetails = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
	public Double getTax() {
		return this.getOrderDetails().stream()
			.map(OrderDetail::getTax)
			.collect(Collectors.summingDouble(Double::doubleValue));
	}

	public Double getPrice() {
		return this.orderDetails.stream()
			.map(OrderDetail::getPrice)
			.collect(Collectors.summingDouble(Double::doubleValue));
	}
	
	public Double getTotalPrice() {
		return this.orderDetails.stream()
			.map(OrderDetail::getTotalPrice)
			.collect(Collectors.summingDouble(Double::doubleValue));
	}
	
	public Double getTotalDiscount() {
		return this.orderDetails.stream()
			.map(od -> {
				return Double.valueOf(od.getDiscount());
			})
			.collect(Collectors.summingDouble(Double::doubleValue));
	}
}