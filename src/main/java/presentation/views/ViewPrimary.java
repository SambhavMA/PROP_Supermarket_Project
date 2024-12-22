package presentation.views;

import presentation.panels.*;

import javax.swing.*;
import java.awt.*;
/**
 *
 * Vista principal del sistema
 *
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu) i Sergio Polo (sergio.polo@estudiantat.upc.edu)
 *
 * <p>Contiene el menu y un panel de contenido que cambia segun la opcion seleccionada del menu.
 * El panel contenido muestra los paneles relacionados con la gestion de productos, la gestion de tablas de similitud o la gestion de distribuciones.
 * </p>
 *
 */

public class ViewPrimary {

    private JFrame frame = new JFrame("Primary View");
    private WelcomePanel welcomePanel;
    private ProductsManagePanel productsManagePanel;
    private SimilarityTablesManagePanel similarityTablesManagePanel;
    private DistributionsManagePanel distributionsManagePanel;
    private AddProductPanel addProductPanel;
    private ProductPanel productPanel;
    private AddSimilarityTablePanel1 addSimilarityTablePanel1;
    private AddSimilarityTablePanel2 addSimilarityTablePanel2;
    private SimilarityTablePanel similarityTablePanel;
    private DistributionPanel distributionPanel;
    private GenerateDistributionPanel generateDistributionPanel;
    private JPanel contentPanel = new JPanel();
    private MenuPanel menuPanel;

    /**
     * Constructor de la vista principal
     */
    public ViewPrimary() {
        initializeFrame();
        initializeContentPanel();
    }

    /**
     * Muestra la vista principal
     */
    public void display() {
        frame.setVisible(true);
    }

    /**
     * Inicializa el panel de contenido
     */
    private void initializeContentPanel() {
        contentPanel.setLayout(new BorderLayout());
        menuPanel = new MenuPanel(this);
        contentPanel.add(menuPanel, BorderLayout.NORTH);
        contentPanel.add(getWelcomePanel(), BorderLayout.CENTER);
    }

    /**
     * Inicializa el frame
     */
    private void initializeFrame() {
        frame.setMinimumSize(new Dimension(900, 400));
        frame.setPreferredSize(frame.getMinimumSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(contentPanel);
    }

    /**
     * Cambia el panel de contenido
     * @param changeToPanel panel al que se quiere cambiar
     */
    public void transitionContentPanel(JPanel changeToPanel) {
        contentPanel.remove(contentPanel.getComponent(1));
        contentPanel.add(changeToPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();

        menuPanel.updateButtonColors(changeToPanel);
    }

    // LAZY INIT

    /**
     * Devuelve el panel de bienvenida
     * @return panel de bienvenida
     */
    public WelcomePanel getWelcomePanel() {
        if (welcomePanel == null) welcomePanel = new WelcomePanel();
        return welcomePanel;
    }

    /**
     * Devuelve el panel de gestion de productos y actualiza la lista de productos
     * @return panel de gestion de productos
     */
    public ProductsManagePanel getProductsManagePanel() {
        if (productsManagePanel == null) productsManagePanel = new ProductsManagePanel(this);
        productsManagePanel.updateList();
        return productsManagePanel;
    }

    /**
     * Devuelve el panel de gestion de tablas de similitud y actualiza la lista de tablas de similitud
     * @return panel de gestion de tablas de similitud
     */
    public SimilarityTablesManagePanel getSimilarityTablesManagePanel() {
        if (similarityTablesManagePanel == null) similarityTablesManagePanel = new SimilarityTablesManagePanel(this);
        similarityTablesManagePanel.updateList();
        return similarityTablesManagePanel;
    }

    /**
     * Devuelve el panel de gestion de distribuciones y actualiza la lista de distribuciones
     * @return panel de gestion de distribuciones
     */
    public DistributionsManagePanel getDistributionsManagePanel() {
        if (distributionsManagePanel == null) distributionsManagePanel = new DistributionsManagePanel(this);
        distributionsManagePanel.updateList();
        return distributionsManagePanel;
    }

    /**
     * Devuelve el panel de añadir producto
     * @return panel de añadir producto
     */
    public AddProductPanel getAddProductPanel() {
        if (addProductPanel == null) addProductPanel = new AddProductPanel(this);
        return addProductPanel;
    }

    /**
     * Devuelve el panel de producto
     * @param name nombre del producto
     * @param type tipo del producto
     * @return panel de producto
     */
    public ProductPanel getProductPanel(String name, String type) {
        productPanel = new ProductPanel(this, name, type);
        return productPanel;
    }

    /**
     * Devuelve el panel de tabla de similitud
     * @param id id de la tabla de similitud
     * @return panel de tabla de similitud
     */
    public SimilarityTablePanel getSimilarityTablePanel(int id) {
        similarityTablePanel = new SimilarityTablePanel(this, id);
        return similarityTablePanel;
    }

    /**
     * Devuelve el panel de añadir tabla de similitud y actualiza la lista de tablas de similitud
     * @return panel de añadir tabla de similitud
     */
    public AddSimilarityTablePanel1 getAddSimilarityTablePanel1() {
        if (addSimilarityTablePanel1 == null) addSimilarityTablePanel1 = new AddSimilarityTablePanel1(this);
        addSimilarityTablePanel1.updateList();
        return addSimilarityTablePanel1;
    }

    /**
     * Devuelve el panel de añadir tabla de similitud
     * @param selectedProducts productos seleccionados
     * @return panel de añadir tabla de similitud
     */
    public AddSimilarityTablePanel2 getAddSimilarityTablePanel2(String[] selectedProducts) {
        addSimilarityTablePanel2 = new AddSimilarityTablePanel2(this, selectedProducts);
        return addSimilarityTablePanel2;
    }

    /**
     * Devuelve el panel de distribucion
     * @param id id de la distribucion
     * @return panel de distribucion
     */
    public DistributionPanel getDistributionPanel(int id) {
        distributionPanel = new DistributionPanel(this, id);
        return distributionPanel;
    }

    /**
     * Devuelve el panel de generar distribucion
     * @return panel de generar distribucion
     */
    public GenerateDistributionPanel getGenerateDistributionPanel() {
        generateDistributionPanel = new GenerateDistributionPanel(this);
        return generateDistributionPanel;
    }
}
