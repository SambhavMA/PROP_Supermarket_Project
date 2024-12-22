package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.components.ItemListDistributions;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de gestion de distribuciones
 */
public class DistributionsManagePanel extends JPanel {
    private ItemListDistributions itemListDistributions;
    private JButton generateDistributionButton = new JButton("<html><div style='text-align: center; color: grey;'>Generar distribucion</div></html>");
    private JPanel contentPanel = new JPanel();

    private ViewPrimary viewPrimary;

    /**
     * Constructor de la clase
     * @param viewPrimary vista principal
     */
    public DistributionsManagePanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        compose();
    }

    /**
     * Inicializa los componentes del panel
     */
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

    /**
     * Inicializa la lista de distribuciones
     * @param dataP datos de las distribuciones
     */
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

    /**
     * Actualiza la lista de distribuciones
     */
    public void updateList() {
        refreshContent();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Refresca el contenido del panel
     */
    private void refreshContent() {
        contentPanel.removeAll();

        String[][] dataPresentation = CtrlPresentation.getInstance().getDistributions();

        if (dataPresentation.length > 0) {
            initializeList(dataPresentation);
            contentPanel.add(itemListDistributions, BorderLayout.CENTER);
        } else {
            JPanel noDataPanel = new JPanel();
            noDataPanel.setLayout(new GridBagLayout());

            JLabel noDistributionTitle = new JLabel("No hay distribuciones en el sistema", SwingConstants.CENTER);
            noDistributionTitle.setFont(new Font("Arial", Font.BOLD, 24));

            JLabel textoRecargar = new JLabel("Si has generado una distribución, puede que se esté ejecutando en segundo plano", SwingConstants.CENTER);
            textoRecargar.setFont(new Font("Arial", Font.PLAIN, 12));
            textoRecargar.setForeground(Color.GRAY);

            JButton reloadButton = new JButton("Recargar");
            reloadButton.setFont(new Font("Arial", Font.BOLD, 12));
            reloadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            reloadButton.addActionListener(e -> updateList());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;

            noDataPanel.add(noDistributionTitle, gbc);

            gbc.gridy++;
            noDataPanel.add(textoRecargar, gbc);

            gbc.gridy++;
            noDataPanel.add(reloadButton, gbc);

            contentPanel.add(noDataPanel, BorderLayout.CENTER);
        }

        JPanel buttonPanel = new JPanel(new BorderLayout(10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(generateDistributionButton, BorderLayout.CENTER);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    }


}
