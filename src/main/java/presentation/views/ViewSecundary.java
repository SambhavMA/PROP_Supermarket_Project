package presentation.views;

import presentation.components.DefaultButtons;
import presentation.panels.*;

import javax.swing.*;
import java.awt.*;

public class ViewSecundary {

    private JFrame frame = new JFrame("Secundary View");
    private ProductPanel productPanel;
    private SimilarityTablePanel similarityTablePanel;
    private DistributionPanel distributionPanel;
    private GenerateDistributionPanel generateDistributionPanel;
    private JPanel contentPanel = new JPanel();
    private JPanel infoPanel = new JPanel();;
    private JPanel buttonPanel;

    public ViewSecundary(ViewSecundaryPanelsEnum e) {
        initializeFrame();
        initializeComponents();
        transitionInfoPanel(getTypePanel(e));
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

    private void initializeFrame() {
        frame.setMinimumSize(new Dimension(600,300));
        frame.setPreferredSize(frame.getMinimumSize());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.getContentPane().add(contentPanel);
    }

    private void initializeComponents() {
        initializeInfoPanel();
        initializeButtons();
    }

    private void initializeInfoPanel() {
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(infoPanel, BorderLayout.CENTER);
    }

    private void initializeButtons() {
        buttonPanel = new DefaultButtons();
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void transitionInfoPanel(JPanel changeToPanel) {
        contentPanel.remove(infoPanel);
        infoPanel = changeToPanel;
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    public ProductPanel getProductPanel() {
        if (productPanel == null) productPanel = new ProductPanel();
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

    private JPanel getTypePanel(ViewSecundaryPanelsEnum e) {
        switch (e) {
            case PRODUCTPANEL:
                return getProductPanel();
            case SIMILARITYTABLEPANEL:
                return getSimilarityTablePanel();
            case DISTRIBUTIONPANEL:
                return getDistributionPanel();
            case GENERATEDISTRIBUTIONPANEL:
                return getGenerateDistributionPanel();
            default:
                return null;
        }
    }
}
