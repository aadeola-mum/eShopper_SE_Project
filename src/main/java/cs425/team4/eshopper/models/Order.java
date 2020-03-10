package cs425.team4.eshopper.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(nullable = false)
    private String OrderID;

    @NotNull
    @OneToOne
    private User buyer;

    @NotNull
    @PastOrPresent
    private LocalDate date;

    @NotNull
    private Boolean paid;

    @NotNull
    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderDetail> orderDetails;
    //TODO

    @NotNull
    @Min(value = 0)
    private Double totalPrice;

    @NotNull
    @Min(value = 0)
    private Double totalTaxes;

    public Order() {
    }
}
