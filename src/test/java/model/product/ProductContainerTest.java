package model.product;

import com.sun.jdi.event.MonitorContendedEnteredEvent;
import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.HashMap;

public class ProductContainerTest {

    private ProductContainer productContainer;

    @Mock
    private Product mockproduct;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productContainer = productContainer.getInstance();
        mockproduct = mock(Product.class);
        when(mockproduct.getName()).thenReturn("Test Product");
    }

    @Test
    public void testAddProduct() {
        try {
            productContainer.addProduct(mockproduct);
            assertTrue(productContainer.getProducts().containsKey("Test Product"));
            assertEquals(mockproduct, productContainer.getProductByName("Test Product"));
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }

    @Test
    public void testAddProductAlreadyExists() {
        try {
            productContainer.addProduct(mockproduct);
            productContainer.addProduct(mockproduct);
            fail("Should throw ProductAlreadyExistsException");
        } catch (ProductAlreadyExistsException e) {
            assertEquals("The product Test Product already exists in the system.", e.getMessage());
        } catch (Exception e) {
            fail("Should throw ProductAlreadyExistsException");
        }
    }

    @Test
    public void testDeleteProductByName() {
        try {
            productContainer.addProduct(mockproduct);
            productContainer.deleteProductByName("Test Product");
            assertFalse(productContainer.getProducts().containsKey("Test Product"));
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }

    @Test
    public void testDeleteProductByNameNotFound() {
        try {
            productContainer.deleteProductByName("Nonexistent Product");
            fail("Should throw ProductNotFoundException");
        } catch (ProductNotFoundException e) {
            assertEquals("The product Nonexistent Product was not found in the system.", e.getMessage());
        } catch (Exception e) {
            fail("Should throw ProductNotFoundException");
        }
    }
}
