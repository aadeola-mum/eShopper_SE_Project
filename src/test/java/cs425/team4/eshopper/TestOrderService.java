package cs425.team4.eshopper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import cs425.team4.eshopper.dao.OrderDetailRepository;
import cs425.team4.eshopper.dao.OrderRepository;
import cs425.team4.eshopper.dao.ProductRepository;
import cs425.team4.eshopper.models.Order;
import cs425.team4.eshopper.models.OrderDetail;
import cs425.team4.eshopper.models.Product;
import cs425.team4.eshopper.models.ProductCategory;
import cs425.team4.eshopper.models.Role;
import cs425.team4.eshopper.models.User;
import cs425.team4.eshopper.services.OrderDetailService;
import cs425.team4.eshopper.services.OrderService;
import cs425.team4.eshopper.services.ProductService;
import cs425.team4.eshopper.services.Impl.OrderDetailServiceImpl;
import cs425.team4.eshopper.services.Impl.OrderServiceImpl;
import cs425.team4.eshopper.services.Impl.ProductServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestOrderService {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

    private List<Product> products = Arrays.asList(
    		new Product(1L, "ABC","ABC Summary","ABC Description", 20, 1200, 10, true, null, null, new ProductCategory("ABC Category")),
    		new Product(2L, "DEF","DEF Summary","DEF Description", 5, 200, 0, false, null, null, new ProductCategory("DEF Category")),
    		new Product(3L, "GHI","GHI Summary","GHI Description", 10, 1500, 5, true, null, null, new ProductCategory("GHI Category")));

    private List<OrderDetail> orderDetails = Arrays.asList(
    		new OrderDetail(1L, "ABC",products.get(0), 20, 10, LocalDate.of(2019, 2, 20), 1200, 25),
    		new OrderDetail(2L, "DEF",products.get(1), 200, 50, LocalDate.of(2019, 3, 5), 450, 10),
    		new OrderDetail(3L, "GHI",products.get(2), 17, 5, LocalDate.of(2019, 4, 19), 200, 20));
    
    private User user = new User(1L);
    
    private List<Order> orders = Arrays.asList(
    		new Order(1L, "12345",user,LocalDate.of(2019, 2, 3),false, orderDetails.subList(0, 1), 20, 1200, 10),
    		new Order(1L, "12345",user,LocalDate.of(2019, 3, 3),true, orderDetails.subList(1, 2), 17, 2000, 5),
    		new Order(1L, "12345",user,LocalDate.of(2019, 5, 3),false, orderDetails, 30, 500, 2));

    
    @BeforeEach
    void setMockOutput() {
        when(orderRepository.findAll()).thenReturn(orders);
        
    }

    @Test
    void testGetOrderDetails() {
        assertEquals(orders, orderService.getAll());
        verify(orderRepository).findAll();
    }

    @Test
    void testGetOrderDetail() {
    	when(orderRepository.findById(orders.get(1).getId())).thenReturn(Optional.of(orders.get(1)));
        assertEquals(orders.get(1), orderService.get(orders.get(1).getId()).orElse(null));
        verify(orderRepository).findById(orders.get(1).getId());
    }

    @Test
    void testGetOrderDetailById() {
    	when(orderRepository.findById(orders.get(0).getId())).thenReturn(Optional.of(orders.get(0)));
        assertEquals(orders.get(0), orderService.get(orders.get(0).getId()).orElse(null));
    }


    @Test
    void testSaveOrderDetails() {
        when(orderRepository.save(orders.get(1))).thenReturn(orders.get(1));
        assertEquals(orders.get(1), orderService.save(orders.get(1)));
        verify(orderRepository).save(orders.get(1));
    }


}
