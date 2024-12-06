package presentation.views;

import presentation.panels.*;

import javax.swing.*;
import java.awt.*;

public class ViewPrimary {

    private JFrame frame = new JFrame("Primary View");
    private WelcomePanel welcomePanel;
    private ProductsManagePanel productsManagePanel;
    private SimilarityTablesManagePanel similarityTablesManagePanel;
    private DistributionsManagePanel distributionsManagePanel;
    private AddProductPanel addProductPanel;
    private ProductPanel productPanel;
    private SimilarityTablePanel similarityTablePanel;
    private DistributionPanel distributionPanel;
    private GenerateDistributionPanel generateDistributionPanel;
    private JPanel contentPanel = new JPanel();
    private MenuPanel menuPanel;
    private JPanel infoPanel = new JPanel();

    public ViewPrimary() {
        initializeFrame();
        initializeComponents();
        transitionInfoPanel(getWelcomePanel());
    }

    public void stop() {
        frame.dispose();
    }

    public void display() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void enable() {
        frame.setEnabled(true);
    }

    public void disable() {
        frame.setEnabled(false);
    }

    private void initializeComponents() {
        initializeInfoPanel();
        initializeMenu();
    }

    private void initializeFrame() {
        frame.setMinimumSize(new Dimension(900, 400));
        frame.setPreferredSize(frame.getMinimumSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(contentPanel);
    }

    private void initializeInfoPanel() {
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(infoPanel, BorderLayout.CENTER);
    }

    private void initializeMenu() {
        menuPanel = new MenuPanel(this);
        contentPanel.add(menuPanel, BorderLayout.NORTH);
    }

    public void transitionInfoPanel(JPanel changeToPanel) {
        contentPanel.remove(infoPanel);
        infoPanel = changeToPanel;
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void transitionContentPanel(JPanel changeToPanel) {
        contentPanel.remove(contentPanel.getComponent(1));
        contentPanel.add(changeToPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();

        menuPanel.updateButtonColors(changeToPanel);
    }

    // LAZY INIT
    public WelcomePanel getWelcomePanel() {
        if (welcomePanel == null) welcomePanel = new WelcomePanel();
        return welcomePanel;
    }

    public ProductsManagePanel getProductsManagePanel() {
        if (productsManagePanel == null) productsManagePanel = new ProductsManagePanel(this);
        productsManagePanel.updateList();
        return productsManagePanel;
    }

    public SimilarityTablesManagePanel getSimilarityTablesManagePanel() {
        if (similarityTablesManagePanel == null) similarityTablesManagePanel = new SimilarityTablesManagePanel();
        return similarityTablesManagePanel;
    }

    public DistributionsManagePanel getDistributionsManagePanel() {
        if (distributionsManagePanel == null) distributionsManagePanel = new DistributionsManagePanel();
        return distributionsManagePanel;
    }

    public AddProductPanel getAddProductPanel() {
        if (addProductPanel == null) addProductPanel = new AddProductPanel(this);
        return addProductPanel;
    }

    public ProductPanel getProductPanel(String name, String type) {
        productPanel = new ProductPanel(this, name, type);
        return productPanel;
    }

    public SimilarityTablePanel getSimilarityTablePanel() {
        if (similarityTablePanel == null) similarityTablePanel = new SimilarityTablePanel();
        return similarityTablePanel;
    }

    public DistributionPanel getDistributionPanel() {
        if (distributionPanel == null) distributionPanel = new DistributionPanel();
        return distributionPanel;
    }

    public GenerateDistributionPanel getGenerateDistributionPanel() {
        if (generateDistributionPanel == null) generateDistributionPanel = new GenerateDistributionPanel();
        return generateDistributionPanel;
    }
}
