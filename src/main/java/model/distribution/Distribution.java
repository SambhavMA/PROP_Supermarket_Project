package model.distribution;

import java.util.Vector;
import java.util.ArrayList;
import java.util.AbstractMap;

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

    public Distribution(int id, Vector<String> order, ArrayList<EnumTypeSections> sections) {
        this.id = id;
        this.order = order;
        this.sections = sections;
        this.usedAlgorithm = null;
    }

    public Distribution(int id, Vector<String> order) {
        this.id = id;
        this.order = order;
        this.sections = null;
        this.usedAlgorithm = null;
    }

    // Esto puede que no sea necesario
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
    public void setOrder(Vector<String> order) {
        this.order = order;
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