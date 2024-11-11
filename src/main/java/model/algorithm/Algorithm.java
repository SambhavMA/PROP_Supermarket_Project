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

    public void execute() {};
    public Solution execute(int initial) { return null; };

    public Solution execute(Solution initialSolution) { return null; };
}
