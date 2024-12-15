package model.product;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 * <p>Clase que representa un producto</p>
 */
public class Product {
    private String name;
    private EnumType type;

    /**
     * Constructor de la clase Product
     * @param name Nombre del producto
     * @param type Tipo del producto
     */
    public Product(String name, EnumType type) {
        this.name = name;
        this.type = type;
    }

    public Product(String name) {
        this.name = name;
        this.type = null;
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

}
