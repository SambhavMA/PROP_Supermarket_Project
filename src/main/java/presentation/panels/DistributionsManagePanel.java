package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.components.ItemListDistributions;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

public class DistributionsManagePanel extends JPanel {
    private ItemListDistributions itemListDistributions;
    private JButton generateDistributionButton = new JButton("<html><div style='text-align: center; color: grey;'>Generar distribucion</div></html>");
    private JPanel contentPanel = new JPanel();

    private ViewPrimary viewPrimary;

    public DistributionsManagePanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        compose();
    }

    private void compose() {
        setLayout(new BorderLayout());

        contentPanel.setLayout(new BorderLayout());

        refreshContent();

        JPanel buttonPanel = new JPanel(new BorderLayout(10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        generateDistributionButton.addActionListener(e -> viewPrimary.transitionContentPanel(viewPrimary.getGenerateDistributionPanel()));

        generateDistributionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonPanel.add(generateDistributionButton, BorderLayout.CENTER);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void initializeList(String[][] dataP) {
        String[] cols = CtrlPresentation.getInstance().getDistributionsCols();

        JComponent[][] data = new JComponent[dataP.length][cols.length];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                data[i][j] = new JLabel(dataP[i][j], SwingConstants.CENTER);
            }
        }

        itemListDistributions = new ItemListDistributions(viewPrimary, this, dataP.length, cols.length, cols, data);
    }

    public void updateList() {
        refreshContent();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void refreshContent() {
        contentPanel.removeAll();

        String[][] dataPresentation = CtrlPresentation.getInstance().getDistributions();

        if (dataPresentation.length > 0) {
            initializeList(dataPresentation);
            contentPanel.add(itemListDistributions, BorderLayout.CENTER);
        } else {
            JLabel noDistributionTitle = new JLabel("No hay distribuciones en el sistema", SwingConstants.CENTER);
            noDistributionTitle.setFont(new Font("Arial", Font.BOLD, 24));
            contentPanel.add(noDistributionTitle, BorderLayout.CENTER);
        }

        JPanel buttonPanel = new JPanel(new BorderLayout(10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(generateDistributionButton, BorderLayout.CENTER);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}
