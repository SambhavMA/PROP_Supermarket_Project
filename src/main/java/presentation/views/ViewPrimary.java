package presentation.views;

import presentation.panels.*;

import javax.swing.*;

public class ViewPrimary {

    private JFrame frame = new JFrame("Primary View");
    private WelcomePanel welcomePanel;
    private ProductsManagePanel productsManagePanel;
    private SimilarityTablesManagePanel similarityTablesManagePanel;
    private DistributionsManagePanel distributionsManagePanel;


    public ViewPrimary() {

    }

    public void start() {
        showWelcomePanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void stop() {

    }

    public void show() {
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
        else welcomePanel.show();
    }

    private void hideWelcomePanel() {
        if (welcomePanel != null) welcomePanel.hide();
    }

    private void showProductsManagePanel() {
        if (productsManagePanel == null) productsManagePanel = new ProductsManagePanel();
        else productsManagePanel.show();
    }

    private void hideProductsManagePanel() {
        if (productsManagePanel != null) productsManagePanel.hide();
    }

    private void showSimilarityTableManagePanel() {
        if (similarityTablesManagePanel == null) similarityTablesManagePanel = new SimilarityTablesManagePanel();
        else similarityTablesManagePanel.show();
    }

    private void hideSimilarityTableManagePanel() {
        if(similarityTablesManagePanel != null) similarityTablesManagePanel.hide();
    }

    private void showDistributionManagePanel() {
        if (distributionsManagePanel == null) distributionsManagePanel = new DistributionsManagePanel();
        else distributionsManagePanel.show();
    }

    private void hideDistributionManagePanel() {
        if(distributionsManagePanel != null) distributionsManagePanel.hide();
    }

}
