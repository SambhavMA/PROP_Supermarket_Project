package presentation.views;

import presentation.panels.*;

import javax.swing.*;
import java.awt.*;

public class ViewPrimary {

    private JFrame frame = new JFrame("Primary View");
    private WelcomePanel welcomePanel = new WelcomePanel();
    private ProductsManagePanel productsManagePanel = new ProductsManagePanel();
    private SimilarityTablesManagePanel similarityTablesManagePanel = new SimilarityTablesManagePanel();
    private DistributionsManagePanel distributionsManagePanel = new DistributionsManagePanel();
    private JPanel contentPanel = new JPanel();
    private JPanel menu = new JPanel();
    private JPanel infoPanel = new JPanel();

    public ViewPrimary() {
    }

    public void start() {
        initializeComponents();
        transitionInfoPanel(welcomePanel);

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

    private void initializeComponents() {
        initializeFrame();
        initializeContentPanel();
    }

    private void initializeFrame() {
        frame.setMinimumSize(new Dimension(700,400));
        frame.setPreferredSize(frame.getMinimumSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(contentPanel);
    }
    
    private void initializeContentPanel() {
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(menu,BorderLayout.NORTH);
        contentPanel.add(infoPanel,BorderLayout.CENTER);
    }


    private void transitionInfoPanel(JPanel changeToPanel) {
        contentPanel.remove(infoPanel);
        infoPanel = changeToPanel;
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}
