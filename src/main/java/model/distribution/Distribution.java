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

    //Distribucion sin algunos parametros?


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
    /*
        Si se inicializa sin usedAlgorithm
        public void setUsedAlgorithm(String usedAlgorithm) {}
     */

    public void changeOrder(AbstractMap.SimpleEntry<Integer, Integer> p) {
        ;
    }
}