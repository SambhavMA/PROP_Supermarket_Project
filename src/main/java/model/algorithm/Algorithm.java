package model.algorithm;

import model.similarity.SimilarityTable;

public abstract class Algorithm {
    protected String name;
    protected String description;

    public Algorithm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(SimilarityTable similarityTable);
}
