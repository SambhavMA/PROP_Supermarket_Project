package model.algorithm;

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

    public Solution execute(int initial, int nProductes) { return null; };

    public Solution execute(Solution solutions[]) { return null; };
}
