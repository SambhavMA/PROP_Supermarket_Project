package model.algorithm;

/**
 * Clase parámetro, contiene un parametro generico para un algoritmo
 *
 * @author Sergio Polo (sergio.polo@estudiantat.upc.edu)
 *
 * @param <T>
 *
 *  <p>Atributos internos:</p>
 *  <ul>
 *    <li><b>name</b>: Matriz de costes.</li>
 *    <li><b>description</b>: Matriz de costes.</li>
 *    <li><b>value</b>: Valor del paraemtro generico.</li>
 *  </ul>
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
