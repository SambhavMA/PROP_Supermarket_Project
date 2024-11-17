package model.distribution;

import java.util.Vector;
import java.util.ArrayList;

public class Distribution {
    private int id;
    private int similarityTableId;
    private double cost;
    private double temps;
    private Vector<String> order;
    private ArrayList<EnumTypeSections> sections;
    private String usedAlgorithm;

    public Distribution(int id, int similarityTableId, Vector<String> order, ArrayList<EnumTypeSections> sections,
                        String usedAlgorithm) {
        this.id = id;
        this.similarityTableId = similarityTableId;
        this.order = order;
        this.sections = sections;
        this.usedAlgorithm = usedAlgorithm;
    }

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
    // mirar que old_order.length == order.length
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

    public void setUsedAlgorithm(String usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }

    // METHODS
    // Controlador: getOrder -> hacer cambios necesarios -> setOrder
}
