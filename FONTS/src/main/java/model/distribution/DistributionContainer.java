package model.distribution;

import model.exceptions.DistributionNotFoundException;
import java.util.HashMap;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 * <p>Clase que representa un contenedor de distribuciones</p>
 */
public class DistributionContainer {
    private static DistributionContainer instance;

    public static DistributionContainer getInstance() {
        if (instance == null) {
            instance = new DistributionContainer();
        }
        return instance;
    }

    private HashMap<Integer, Distribution> distributions = new HashMap<>();
    private int idCounter;

    private DistributionContainer() {
        this.distributions = new HashMap<>();
        this.idCounter = 0;
    }

    public HashMap<Integer, Distribution> getDistributions() {
        return distributions;
    }

    public Distribution getDistributionById(int id) throws DistributionNotFoundException {
        if (!distributions.containsKey(id)) {
            throw new DistributionNotFoundException(id);
        }
        return distributions.get(id);
    }

    /**
     * Añade una distribución al contenedor
     * @param id Identificador de la distribución
     * @param distribution Distribución a añadir
     */
    public void addDistribution(int id, Distribution distribution) {
        distributions.put(id, distribution);
    }

    /**
     * Elimina una distribución del contenedor
     * @param id Identificador de la distribución
     */
    public void deleteDistributionById(int id) {
        distributions.remove(id);
    }

    /**
     * Devuelve el siguiente identificador de distribución
     * @return Identificador de la distribución
     */
    public int newId() {
        return ++idCounter;
    }

    public int getIdCounter() { return idCounter; }

    public int nextId() {
        return idCounter + 1;
    }
}