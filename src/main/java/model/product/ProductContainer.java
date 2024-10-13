package model.product;

import java.util.HashMap;

public class ProductContainer {
    private HashMap<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public Product getProductByName(String name) {
        return products.get(name);
    }
}