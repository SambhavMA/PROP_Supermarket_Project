package model.algorithm;

/**
 * Clase parámetro, contiene los parámetros de entrada para ejecutar cada algoritmo
 * @param <T>
 */
public class Parameter<T> {
    private String name;
    private String description;
    private T value;

    public Parameter(String name, String description, T value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public T getValue() {
        return value;
    }
}
