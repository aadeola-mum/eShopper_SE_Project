package cs425.team4.eshopper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import cs425.team4.eshopper.dao.ProductRepository;
import cs425.team4.eshopper.models.Product;
import cs425.team4.eshopper.models.ProductCategory;
import cs425.team4.eshopper.services.ProductService;
import cs425.team4.eshopper.services.Impl.ProductServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestProductService {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService = new ProductServiceImpl(productRepository);

    private List<Product> products = Arrays.asList(
    		new Product(1L, "ABC","ABC Summary","ABC Description", 20, 1200, 10, true, null, null, new ProductCategory("ABC Category")),
    		new Product(2L, "DEF","DEF Summary","DEF Description", 5, 200, 0, false, null, null, new ProductCategory("DEF Category")),
    		new Product(3L, "GHI","GHI Summary","GHI Description", 10, 1500, 5, true, null, null, new ProductCategory("GHI Category")));

    @BeforeEach
    void setMockOutput() {
        when(productRepository.findAll()).thenReturn(products);
        
    }

    @Test
    void testGetProducts() {
        assertEquals(products, productService.getAll());
        verify(productRepository).findAll();
    }

    @Test
    void testGetProduct() {
    	when(productService.get(products.get(1).getId())).thenReturn(Optional.of(products.get(1)));
        assertEquals(products.get(1), productService.get(products.get(1).getId()).orElse(null));
    }

    @Test
    void testGetProductByProductId() {
    	when(productService.get(1L)).thenReturn(Optional.of(products.get(0)));
        assertEquals(products.get(0), productService.get(products.get(0).getId()).orElse(null));
    }


    @Test
    void testSaveProduct() {
        when(productRepository.save(products.get(1))).thenReturn(products.get(1));
        assertEquals(products.get(1), productService.save(products.get(1)));
        verify(productRepository).save(products.get(1));
    }


}
