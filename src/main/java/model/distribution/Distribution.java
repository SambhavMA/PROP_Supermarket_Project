package model.distribution;

import java.util.Vector;
import java.util.ArrayList;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 *
 * <p>Clase que representa una distribución de productos</p>
 */
public class Distribution {
    private int id;
    private int similarityTableId;
    private double cost;
    private double temps;
    private Vector<String> order;
    private ArrayList<EnumTypeSections> sections;
    private String usedAlgorithm;

    /**
     * Constructora de la clase Distribution con secciones (actualmente no se usa)
     * @param id Identificador de la distribución
     * @param similarityTableId Identificador de la tabla de similitud
     * @param order Orden de los productos
     * @param sections Secciones de los productos
     * @param usedAlgorithm Algoritmo usado para la distribución
     */
    public Distribution(int id, int similarityTableId, Vector<String> order, ArrayList<EnumTypeSections> sections,
                        String usedAlgorithm) {
        this.id = id;
        this.similarityTableId = similarityTableId;
        this.order = order;
        this.sections = sections;
        this.usedAlgorithm = usedAlgorithm;
        this.temps = temps;
    }

    /**
     * Constructora de la clase Distribution sin secciones
     * @param id Identificador de la distribución
     * @param similarityTableId Identificador de la tabla de similitud
     * @param cost Coste de la distribución
     * @param order Orden de los productos
     * @param usedAlgorithm Algoritmo usado para la distribución
     * @param temps Tiempo de ejecución del algoritmo
     */
    public Distribution(int id, int similarityTableId, double cost, Vector<String> order,
                        String usedAlgorithm, double temps) {
        this.id = id;
        this.similarityTableId = similarityTableId;
        this.cost = cost;
        this.temps = temps;
        this.order = order;
        this.sections = null;
        this.usedAlgorithm = usedAlgorithm;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public double getTemps() {
        return temps;
    }

    public Vector<String> getOrder() {
        return order;
    }

    public int getSimilarityTableId() {
        return similarityTableId;
    }

    public ArrayList<EnumTypeSections> getSections() {
        return sections;
    }

    public String getUsedAlgorithm() {
        return usedAlgorithm;
    }

    // SETTERS

    /**
     * Cambia el orden de dos productos
     * @param p1 Producto 1
     * @param p2 Producto 2
     */
    public void changeOrder(String p1, String p2) {
        int index1 = order.indexOf(p1);
        int index2 = order.indexOf(p2);
        String aux = order.get(index1);
        order.set(index1, order.get(index2));
        order.set(index2, aux);
    }

    public void setSections(ArrayList<EnumTypeSections> sections) {
        this.sections = sections;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setUsedAlgorithm(String usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }
}
