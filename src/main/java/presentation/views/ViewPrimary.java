package presentation.views;

import presentation.panels.*;

import javax.swing.*;

public class ViewPrimary {

    private JFrame frame = new JFrame("Primary View");
    private WelcomePanel welcomePanel;
    private ProductsManagePanel productsManagePanel;
    private SimilarityTablesManagePanel similarityTablesManagePanel;
    private DistributionsManagePanel distributionsManagePanel;

    private JButton buttonLlamadaDominio = new JButton("Llamada Dominio");

    public ViewPrimary() {
    }

    public void start() {
        showWelcomePanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void stop() {

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

    private void showWelcomePanel() {
        if (welcomePanel == null) welcomePanel = new WelcomePanel();
        else welcomePanel.setVisible(true);
    }

    private void hideWelcomePanel() {
        if (welcomePanel != null) welcomePanel.setVisible(false);
    }

    private void showProductsManagePanel() {
        if (productsManagePanel == null) productsManagePanel = new ProductsManagePanel();
        else productsManagePanel.setVisible(true);
    }

    private void hideProductsManagePanel() {
        if (productsManagePanel != null) productsManagePanel.setVisible(false);
    }

    private void showSimilarityTableManagePanel() {
        if (similarityTablesManagePanel == null) similarityTablesManagePanel = new SimilarityTablesManagePanel();
        else similarityTablesManagePanel.setVisible(true);
    }

    private void hideSimilarityTableManagePanel() {
        if(similarityTablesManagePanel != null) similarityTablesManagePanel.setVisible(false);
    }

    private void showDistributionManagePanel() {
        if (distributionsManagePanel == null) distributionsManagePanel = new DistributionsManagePanel();
        else distributionsManagePanel.setVisible(true);
    }

    private void hideDistributionManagePanel() {
        if(distributionsManagePanel != null) distributionsManagePanel.setVisible(false);
    }

}
