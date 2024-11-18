package model.product;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 */
public class Product {
    private String name;
    private EnumType type;

    // CONSTRUCTORS
    public Product(String name, EnumType type) {
        this.name = name;
        this.type = type;
    }

    public Product(String name) {
        this.name = name;
        this.type = null;
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public EnumType getType() {
        return type;
    }

    // SETTERS

    public void setType(EnumType type) {
        this.type = type;
    }

    // METHODS
}
