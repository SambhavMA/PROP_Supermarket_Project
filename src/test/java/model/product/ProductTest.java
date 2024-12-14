package model.product;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// TODO: Mirar que son els mocks
public class ProductTest {

    private Product product;
    private Product productWithType;
    private EnumType sampleType;

    @Before
    public void setUp() {
        sampleType = EnumType.FRUTA;
        product = new Product("Test Product");
        productWithType = new Product("Test Product with Type", sampleType);
    }

    @Test
    public void testConstructorWithoutType() {
        assertEquals("Test Product", product.getName());
        assertNull(product.getType());
    }

    @Test
    public void testConstructorWithType() {
        assertEquals("Test Product with Type", productWithType.getName());
        assertEquals(sampleType, productWithType.getType());
    }
}
