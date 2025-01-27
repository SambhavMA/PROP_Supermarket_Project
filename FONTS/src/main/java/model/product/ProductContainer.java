package model.product;

import model.exceptions.ProductAlreadyExistsException;
import model.exceptions.ProductNotFoundException;

import java.util.HashMap;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 * <p>Clase que representa un contenedor de productos</p>
 */
public class ProductContainer {
    private static ProductContainer instance;

    public static ProductContainer getInstance() {
        if (instance == null) {
            instance = new ProductContainer();
        }
        return instance;
    }

    private HashMap<String, Product> products = new HashMap<>();

    /**
     * Metodo que añade un producto al contenedor
     * @param product producto a añadir
     * @throws ProductAlreadyExistsException si el producto ya existe
     */
    public void addProduct(Product product)  throws ProductAlreadyExistsException {
        String name = product.getName();
        if (products.containsKey(name)) {
            throw new ProductAlreadyExistsException(name);
        }
        products.put(name, product);
        new Product(name, product.getType());
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public Product getProductByName(String name) throws ProductNotFoundException {
        if (products.containsKey(name)) {
            return products.get(name);
        }
        throw new ProductNotFoundException(name);
    }

    /**
     * Metodo que elimina un producto del contenedor
     * @param name nombre del producto a eliminar
     * @throws ProductNotFoundException si el producto no existe
     */
    public void deleteProductByName(String name) throws ProductNotFoundException {
        if (!products.containsKey(name)) {
            throw new ProductNotFoundException(name);
        }
        products.remove(name);
    }
}
