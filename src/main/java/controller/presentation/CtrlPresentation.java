package controller.presentation;


import controller.ControllerDomini;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class CtrlPresentation {
    private ControllerDomini controllerDomini;
    private ViewPrimary viewPrimary;

    private CtrlPresentation() {
    }

    private static class CtrlPresentationHolder {
        private static final CtrlPresentation INSTANCE = new CtrlPresentation();
    }

    public static CtrlPresentation getInstance() {
        return CtrlPresentationHolder.INSTANCE;
    }

    /**
     * Inicia la presentacion del programa
     */
    public void startPresentation() {
        controllerDomini = ControllerDomini.getInstance();
        viewPrimary = new ViewPrimary();
        viewPrimary.display();
    }

    /**
     * Finaliza la presentacion del programa
     */
    public void endPresentation() {
        System.exit(0);
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

    /**
     * Elimina un producto por su id
     * @param name nombre del producto a eliminar
     *
     */
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

    /**
     * Añade un producto a la base de datos
     * @param name nombre del producto
     * @param type tipo del producto
     * @return null si se ha añadido correctamente, mensaje de error en caso contrario
     */
    public String addProduct(String name, String type) {
        try {
            controllerDomini.addProduct(name, type);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Modifica un producto de la base de datos
     * @param name nombre del producto
     * @param type tipo del producto
     * @return null si se ha modificado correctamente, mensaje de error en caso contrario
     */
    public String modifyProduct(String name, String type) {
        try {
            controllerDomini.modifyProduct(name, type);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public int getSimilarityTableNextId() {
        return controllerDomini.getSimilarityTableNextId();
    }

    /**
     * Añade una tabla de similitud a la base de datos
     * @param products productos de la tabla
     * @param relations relaciones de la tabla
     * @return null si se ha añadido correctamente, mensaje de error en caso contrario
     */
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

    /**
     * Modifica una tabla de similitud de la base de datos
     * @param id id de la tabla a modificar
     * @param nuevasSimilitudes nuevas similitudes de la tabla
     */
    public void modifySimilarityTable(int id, List<Pair<Pair<String, String>, Double>> nuevasSimilitudes) {
        try {
            controllerDomini.modifySimilarityTable(id, nuevasSimilitudes);
        } catch (Exception e) {
            // nothing
        }
    }

    /**
     * Elimina una tabla de similitud de la base de datos
     * @param id id de la tabla a eliminar
     * @return null si se ha eliminado correctamente, mensaje de error en caso contrario
     */
    public String deleteSimilarityTable(int id) {
        try {
            controllerDomini.deleteSimilarityTable(id);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String[] getSimilarityTablesCols() {
        return new String[] {"Id"};
    }

    public String[] getSimilarityTables() {
        return controllerDomini.getSimilarityTables();
    }

    public String[] getDistributionsCols() {
        return new String[] {"Id", "Tabla de similitud", "Algoritmo generador", "Semejanza total", "Tiempo (ms)"};
    }

    public String[][] getDistributions() {
        return controllerDomini.getDistributions();
    }

    /**
     * Elimina una distribucion de la base de datos
     * @param id id de la distribucion a eliminar
     * @return null si se ha eliminado correctamente, mensaje de error en caso contrario
     */
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

    /**
     * Añade una distribucion a la base de datos
     * @param stId id de la tabla de similitud
     * @param usedAlgorithm algoritmo usado para generar la distribucion
     * @return true si se ha generado correctamente, mensaje de error en caso contrario
     */
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

    /**
     * Importa productos de un fichero
     */
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

    /**
     * Guarda los productos en un fichero
     */
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

    /**
     * Guarda las tablas de similitud en un fichero
     */
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

    /**
     * Importa tablas de similitud de un fichero
     */
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
