package cs425.team4.eshopper.models;

import javax.persistence.*;

@Entity(name = "order-details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private Order order;
    //TODO

}
