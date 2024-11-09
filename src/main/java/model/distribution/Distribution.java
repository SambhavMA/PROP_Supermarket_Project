package model.distribution;

import java.util.Vector;
import java.util.ArrayList;

public class Distribution {
    private int id;
    private Vector<String> order;
    private ArrayList<EnumTypeSections> sections;
    private String usedAlgorithm;

    public Distribution(int id, Vector<String> order, ArrayList<EnumTypeSections> sections, String usedAlgorithm) {
        this.id = id;
        this.order = order;
        this.sections = sections;
        this.usedAlgorithm = usedAlgorithm;
    }

    public Distribution(int id, Vector<String> order, String usedAlgorithm) {
        this.id = id;
        this.order = order;
        this.sections = null;
        this.usedAlgorithm = usedAlgorithm;
    }

    public Distribution(int id, Vector<String> order) {
        this.id = id;
        this.order = order;
        this.sections = null;
        this.usedAlgorithm = null;
    }

    // Constructor con solo id
    public Distribution(int id) {
        this.id = id;
        this.order = null;
        this.sections = null;
        this.usedAlgorithm = null;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public Vector<String> getOrder() {
        return order;
    }

    public ArrayList<EnumTypeSections> getSections() {
        return sections;
    }

    public String getUsedAlgorithm() {
        return usedAlgorithm;
    }

    // SETTERS
    // mirar que old_order.length == order.length
    public void setOrder(Vector<String> order) {
        if (this.order.isEmpty()) {
            this.order = order;
        } else if (this.order.size() == order.size()) {
            this.order = order;
        } else {
            Exception e = new Exception("The new order has a different size than the old one");
        }
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
