package controller.presentation;


import controller.ControllerDomini;
import model.exceptions.AlgorithmException;
import model.exceptions.MSTTriangleInequalityException;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Controlador de la capa de presentación
 * Comunica la interfaz gráfica con la capa de dominio. Convierte los datos de la capa de dominio en datos que la
 * interfaz gráfica pueda mostrar y gestiona las excpeciones que puedan surgir.
 */
public class CtrlPresentation {
    private ControllerDomini controllerDomini;
    private ViewPrimary viewPrimary;

    /**
     * Constructora privada de la clase
     */
    private CtrlPresentation() {
    }

    /**
     * Clase interna que implementa el patrón Singleton
     */
    private static class CtrlPresentationHolder {
        private static final CtrlPresentation INSTANCE = new CtrlPresentation();
    }

    /**
     * Devuelve la instancia de la clase
     * @return instancia de la clase
     */
    public static CtrlPresentation getInstance() {
        return CtrlPresentationHolder.INSTANCE;
    }

    /**
     * Inicia la presentacion del programa. Creando el dominio y la vista principal, mostrando esta última
     */
    public void startPresentation() {
        controllerDomini = ControllerDomini.getInstance();
        viewPrimary = new ViewPrimary();
        viewPrimary.display();
    }

    /**
     * Devuelve la vista principal
     * @return vista principal
     */
    public ViewPrimary getViewPrimary() {
        return viewPrimary;
    }

    /**
     * Devuelve los productos que pide al controlador de dominio
     * @return productos de cargados en memoria
     */
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

    /**
     * Devuelve las columnas de los productos. Implementado de esta forma para facilitar la modificación de las columnas
     * en un futuro si se añaden más atributos a los productos
     * @return columnas de los productos
     */
    public String[] getProductsCols() {
        return new String[] {"Nombre", "Tipo"};
    }

    /**
     * Devuelve los tipos de productos que hay en memoria
     * @return lista de tipos de productos
     */
    public String[] getProductTypes() {
        return controllerDomini.getProductsTypes();
    }

    /**
     * Elimina un producto por su id y gestiona las excepciones que puedan surgir
     * @param name nombre del producto a eliminar
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
     * Añade un producto al conjunto de productos
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
     * Modifica un producto del conjunto de productos
     * @param name nombre del producto
     * @param type nuevo tipo del producto
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

    /**
     * Devuelve el siguiente id de tabla de similitud disponible
     * @return siguiente id de tabla de similitud disponible
     */
    public int getSimilarityTableNextId() {
        return controllerDomini.getSimilarityTableNextId();
    }

    /**
     * Añade una tabla de similitud al conjunto de tablas de similitud
     * @param products productos de la tabla a añadir
     * @param relations relaciones de la tabla a añadir
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

    /**
     * Devuelve una tabla de similitud del conjunto de tablas de similitud
     * @param id id de la tabla a devolver
     * @return tabla de similitud
     */
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
        } catch (MSTTriangleInequalityException e) {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    e.getMessage(),
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE
            );
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

    public void swapProducts(int distributionId, String product1, String product2) throws Exception{
        List<Pair<String, String>> changes = new ArrayList<>();
        changes.add(new Pair<>(product1, product2));

        controllerDomini.modifyDistribution(distributionId, changes);

    }

    /**
     * Importa productos de un fichero
     */
    public void importProducts() throws Exception{
        controllerDomini.importProducts();
    }

    /**
     * Guarda los productos en un fichero
     */
    public void saveProducts() throws Exception{
        controllerDomini.exportProducts();
    }

    /**
     * Guarda las tablas de similitud en un fichero
     */
    public void saveSimilarityTables() throws Exception{
        controllerDomini.exportSimilarityTables();
    }

    /**
     * Importa tablas de similitud de un fichero
     */
    public void importSimilarityTables() throws Exception{
        controllerDomini.importSimilarityTables();
    }
}
