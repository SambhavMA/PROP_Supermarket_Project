package controller.presentation;


import controller.ControllerDomini;
import controller.ControllerPersistencia;
import model.exceptions.ProductNotFoundException;
import presentation.views.ViewPrimary;
import presentation.views.ViewSecundary;
import utils.Pair;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class CtrlPresentation {
    private ControllerDomini controllerDomini;
    private ControllerPersistencia controllerPersistencia;
    private ViewPrimary viewPrimary;
    private ViewSecundary viewSecundary;

    private CtrlPresentation() {
    }

    private static class CtrlPresentationHolder {
        private static final CtrlPresentation INSTANCE = new CtrlPresentation();
    }

    public static CtrlPresentation getInstance() {
        return CtrlPresentationHolder.INSTANCE;
    }

    public void startPresentation() {
        controllerDomini = ControllerDomini.getInstance();
        controllerPersistencia = ControllerPersistencia.getInstance();
        viewPrimary = new ViewPrimary();
        viewPrimary.display();
    }

    public void endPresentation() {
        System.exit(0);
    }

    /**
     * Transiciona de la vista principal a la vista secundaria.
     * Deshabilita la principal que se seguira mostrando, mientras la secundaria se ejecuta en primer plano
     */
    public void transitionPrimary_to_Secundary() {
        viewPrimary.disable();
        viewSecundary = new ViewSecundary();
        viewSecundary.enable();
        viewSecundary.display();
    }

    /**
     * Transiciona de la vista secundaria a la vista primaria.
     * Borra la vista secundaria, y habilita y muestra la vista principal en primer plano
     */
    public void transitionSecundary_to_Primary(){
        viewSecundary.stop();
        viewSecundary = null;
        viewPrimary.enable();
        viewPrimary.display();
    }

    public ViewPrimary getViewPrimary() {
        return viewPrimary;
    }

    public Pair<String, String>[] getProducts() {
        try {
            return controllerDomini.getProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    public String[] getProductsCols() {
        return new String[] {"Nombre", "Tipo"};
    }

    public String[] getProductTypes() {
        return controllerDomini.getProductsTypes();
    }

    public void deleteProductById(String name) {
        try {
            controllerDomini.deleteProduct(name);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public String addProduct(String name, String type) {
        try {
            controllerDomini.addProduct(name, type);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String modifyProduct(String name, String type) {
        try {
            controllerDomini.modifyProduct(name, type);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void testProducts() {
        try {
            controllerDomini.testingAddingProducts();
        } catch (Exception e) {

        }

    }

    // SIMILARITY TABLE FUNCTIONS
    public int getSimilarityTableNextId() {
        return controllerDomini.getSimilarityTableNextId();
    }

    public String addSimilarityTable(String[] products, String[] relations) {
        try {
            controllerDomini.addSimilarityTable(products, relations);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Pair<Vector<Pair<String, Integer>>, double[][]> getSimilarityTable(int id) {
        try {
            return controllerDomini.getSimilarityTable(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void modifySimilarityTable(int id, List<Pair<Pair<String, String>, Double>> nuevasSimilitudes) {
        try {
            controllerDomini.modifySimilarityTable(id, nuevasSimilitudes);
        } catch (Exception e) {
            // nothing
        }
    }

    public String deleteSimilarityTable(int id) {
        try {
            controllerDomini.deleteSimilarityTable(id);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void testSimilarityTables() {

    }

    public String[] getSimilarityTablesCols() {
        return new String[] {"Id"};
    }

    public String[] getSimilarityTables() {
        return controllerDomini.getSimilarityTables();
    }

    // DISTRIBUTION FUNCTIONS
    public String[] getDistributionsCols() {
        return new String[] {"Id", "Tabla de similitud", "Algoritmo generador", "Coste"};
    }

    public String[][] getDistributions() {
        return controllerDomini.getDistributions();
    }

    public String deleteDistribution(int id) {
        try {
            controllerDomini.deleteDistribution(id);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public String[] getAlgorithms() {
        return controllerDomini.getAlgorithms();
    }

    public String[][] getDistribution(int id) {
        try {
            return controllerDomini.getDistribution(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return new String[0][0];
        }
    }

    public int getDistributionNextId() {
        return controllerDomini.getDistributionNextId();
    }

    public boolean generateDistribution(int stId, String usedAlgorithm) {
        try {
            controllerDomini.generateDistribution(stId, usedAlgorithm);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

    }

    public void importProducts() {
        try {
            controllerDomini.importProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void saveProducts() {
        try {
            controllerDomini.exportProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void saveSimilarityTables() {
        try {
            controllerDomini.exportSimilarityTables();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void importSimilarityTables() {
        try {
            controllerDomini.importSimilarityTables();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
