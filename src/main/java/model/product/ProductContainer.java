package model.product;

import java.util.HashMap;

public class ProductContainer {
    private HashMap<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public Product getProductByName(String name) {
        if (products.containsKey(name)) {
            return products.get(name);
        }
        return null;
    }

    public int deleteProductByName(String name) {
        if (products.containsKey(name)) {
            products.remove(name);
            return 1;
        }
        return 0; // devuelve 1 si falla el delete
    }
}