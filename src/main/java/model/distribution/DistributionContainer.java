package model.distribution;

import java.util.HashMap;

public class DistributionContainer {
    private HashMap<Integer, Distribution> distributions = new HashMap<>();

    public Distribution getDistributionById(int id) {
        return distributions.get(id);
    }
}