package controller;

import model.algorithm.AlgorithmController;
import model.algorithm.AlgorithmsNames;
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

    /*
    public void testingAlgorithm() throws Exception {
        double[][] costes = {
            {0.0, 0.2, 0.4, 0.6},
            {0.2, 0.0, 0.8, 0.3},
            {0.4, 0.8, 0.0, 0.7},
            {0.6, 0.3, 0.7, 0.0}
        };
        AlgorithmController alg = new AlgorithmController(costes);
        String[] s = alg.getAlgorithms();
        Object[] data = alg.executeAlgorithm(AlgorithmsNames.NN);
    }*/
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
