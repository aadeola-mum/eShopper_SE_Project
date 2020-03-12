package cs425.team4.eshopper.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long orderId;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    private Float discount;

    @NotNull
    @Column(nullable = false)
    @Min(1)
    private Integer quantity;

    @PastOrPresent
    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    public OrderDetail() {
    }

    public OrderDetail(Long orderId, Product product, Float discount, Integer quantity, LocalDate date) {
        this.orderId = orderId;
        this.product = product;
        this.discount = discount;
        this.quantity = quantity;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
	public Double getPrice() {
		Double unitPrice = this.getProduct().getPrice();
		int quantity = this.getQuantity();
		return quantity * unitPrice;
	}

	public Double getTax() {
		Double tax = this.getProduct().getCategory().getTaxInPercentage();
		return this.getPrice() * tax;
	}

	public Double getTotalPrice() {
		Double price = this.getPrice();
		Double discount = Double.valueOf(this.getDiscount());
		return price + this.getTax() - discount;
	}
    
}
