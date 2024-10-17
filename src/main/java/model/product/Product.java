package model.product;

public class Product {
    private String name;
    private EnumType type;

    public Product(String name, EnumType type) {
        this.name = name;
        this.type = type;
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public EnumType getType() {
        return type;
    }

    public void setType(EnumType type) {
        this.type = type;
    }

    // Constructor, getters y setters
}
