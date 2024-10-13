package controller;

import model.product.ProductContainer;
import model.product.Product;
import model.product.EnumType;
import model.distribution.DistributionContainer;
import model.similarity.SimilarityTableContainer;

public class Controller {
    private ProductContainer productContainer;
    private DistributionContainer distributionContainer;
    private SimilarityTableContainer similarityTableContainer;

    // methods
    // algorithm
    // distribution
    // product
    public void addProduct(String name, EnumType type) {
        Product product = new Product(name, type);
        productContainer.addProduct(product);
        System.out.println("Product added: " + name);
    }
    public Product getProductByName(String name) {
        return productContainer.getProductByName(name);
    }
    // similarity
}
