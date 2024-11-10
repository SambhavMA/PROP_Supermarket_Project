package model.algorithm;

public enum AlgorithmsNames {
    NN("Nearest Neighbor"),
    HC("Hill Climbing")
    ;

    private final String text;

    /**
     * @param text
     */
    AlgorithmsNames(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
