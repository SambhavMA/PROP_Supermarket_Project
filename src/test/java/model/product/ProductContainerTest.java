package model.product;

import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

public class ProductContainerTest {

    private ProductContainer productContainer;

    @Before
    public void setUp() {
        productContainer = new ProductContainer();
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("Test Product");
        try {
            productContainer.addProduct(product);
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
        HashMap<String, Product> products = productContainer.getProducts();
        assertTrue(products.containsKey("Test Product"));
        assertEquals(product, products.getProductByName("Test Product"));
    }

    @Test
    public void testAddProductAlreadyExists() {
        Product product = new Product("Test Product");
        try {
            productContainer.addProduct(product);
            productContainer.addProduct(product);
            fail("Should throw ProductAlreadyExistsException");
        } catch (ProductAlreadyExistsException e) {
            assertEquals("The product Test Product already exists in the system.", e.getMessage());
        } catch (Exception e) {
            fail("Should throw ProductAlreadyExistsException");
        }
    }

    @Test
    public void testGetProducts() {
        Product product = new Product("Test Product");
        try {
            productContainer.addProduct(product);
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
        HashMap<String, Product> products = productContainer.getProducts();
        assertTrue(products.containsKey("Test Product"));
        assertEquals(product, products.get("Test Product"));
    }

    @Test
    public void testGetProductByName() {
        Product product = new Product("Test Product");
        try {
            productContainer.addProduct(product);
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
        try {
            assertEquals(product, productContainer.getProductByName("Test Product"));
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }

    @Test
    public void testGetProductByNameNotFound() {
        try {
            productContainer.getProductByName("Test Product");
            fail("Should throw ProductNotFoundException");
        } catch (ProductNotFoundException e) {
            assertEquals("The product Test Product was not found in the system.", e.getMessage());
        } catch (Exception e) {
            fail("Should throw ProductNotFoundException");
        }
    }

    @Test
    public void testDeleteProductByName() {
        Product product = new Product("Test Product");
        try {
            productContainer.addProduct(product);
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
        try {
            productContainer.deleteProductByName("Test Product");
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
        HashMap<String, Product> products = productContainer.getProducts();
        assertFalse(products.containsKey("Test Product"));
    }

    @Test
    public void testDeleteProductByNameNotFound() {
        try {
            productContainer.deleteProductByName("Test Product");
            fail("Should throw ProductNotFoundException");
        } catch (ProductNotFoundException e) {
            assertEquals("The product Test Product was not found in the system.", e.getMessage());
        } catch (Exception e) {
            fail("Should throw ProductNotFoundException");
        }
    }
}
