package model.algorithm;

/*
 * @author Sergio Polo (sergio.polo@estudiantat.upc.edu)
 * 
 * Clase Algoritmo, es una clase abstracta que representa a un algoritmo
 * 
 * <p>Las implementaciones de los distintos tipos de algoritmos estan en sus respectivas clases</p>
 * @see NearestNeighbor
 * @see HillClimbing
 */
public abstract class Algorithm {
    /*
     * Nombre del algoritmo usado
     */
    protected String name;
    /*
     * Descripción. del algoritmo usado
     */
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

    /*
     * Ejecuta un algoritmo, esta función es usada en el algoritmo Nearest Neighbor
     * @see NearestNeighbor
     */
    public Solution execute(int initial, int nProductes) { return null; };

    /*
     * Ejecuta un algoritmo, esta función es usada en el algoritmo Hill CLimbing
     * @see HillClimbing
     */
    public Solution execute(Solution solutions[]) { return null; };
}
