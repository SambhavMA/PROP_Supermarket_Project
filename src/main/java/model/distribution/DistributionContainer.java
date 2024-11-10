package model.distribution;

import java.util.HashMap;

public class DistributionContainer {
    private HashMap<Integer, Distribution> distributions = new HashMap<>();
    private int idCounter;

    //Constructora
    public DistributionContainer() {
        this.distributions = new HashMap<>();
        this.idCounter = 0;
    }

    public HashMap<Integer, Distribution> getDistributions() {
        return distributions;
    }

    public Distribution getDistributionById(int id){
        return distributions.get(id);
    }

    public void addDistribution(Distribution distribution){
        distributions.put(newId(), distribution);
    }

    public void deleteDistributionById(int id){
        distributions.remove(id);
    }

    public int newId() {
        return ++idCounter;
    }
}