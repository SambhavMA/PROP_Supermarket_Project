package controller;

import model.distribution.Distribution;
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
    public void generateDistribution(Distribution distribution) {
        distributionContainer.addDistribution(distribution);
        // System.out.println("Distribution generated: " + distribution.getId());
    }
//    public void modifyDistribution(Distribution distribution) {
//        distributionContainer.addDistribution(distribution);
//        // System.out.println("Distribution modified: " + distribution.getId());
//    }
    public void deleteDistribution(int id) {
        distributionContainer.deleteDistributionById(id);
        // System.out.println("Distribution deleted: " + id);
    }
    public void checkDistribution(int id) {
        Distribution distribution = distributionContainer.getDistributionById(id);
        if (distribution != null) {
            // System.out.println("Distribution found: " + id);
        } else {
            // System.out.println("Distribution not found: " + id);
        }
    }
    // product
    public void addProduct(String name, EnumType type) {
        Product product = new Product(name, type);
        productContainer.addProduct(product);
        // System.out.println("Product added: " + name);
    }
    public void modifyProduct(String name, EnumType type) {
        Product product = productContainer.getProductByName(name);
        product.setType(type);
        // System.out.println("Product modified: " + name);
    }
    public void deleteProduct(String name) {
        productContainer.deleteProductByName(name);
        // System.out.println("Product deleted: " + name);
    }
    public void checkProduct(String name) {
        Product product = productContainer.getProductByName(name);
        if (product != null) {
            // System.out.println("Product found: " + name);
        } else {
            // System.out.println("Product not found: " + name);
        }
    }
    // similarity tables
}
