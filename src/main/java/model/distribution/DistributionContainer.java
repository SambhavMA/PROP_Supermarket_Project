package model.distribution;

import model.exceptions.DistributionNotFoundException;
import java.util.HashMap;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 * <p>Clase que representa un contenedor de distribuciones</p>
 */
public class DistributionContainer {
    private HashMap<Integer, Distribution> distributions = new HashMap<>();
    private int idCounter;

    // Constructora
    public DistributionContainer() {
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

    public void addDistribution(int id, Distribution distribution) {
        distributions.put(id, distribution);
    }

    public void deleteDistributionById(int id) {
        distributions.remove(id);
    }

    public int newId() {
        return ++idCounter;
    }

    public int getIdCounter() { return idCounter; }
}