package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.components.ItemListSimilarityTables;
import presentation.components.ItemListSimilarityTables;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de gestion de tablas de similitud
 */
public class SimilarityTablesManagePanel extends JPanel {
    private ItemListSimilarityTables itemListSimilarityTables;
    private JButton addSimilarityTableButton = new JButton("<html><div style='text-align: center; color: grey;'>Añadir tabla de similitud</div></html>");
    private JButton importSimilarityTablesButton = new JButton("<html><div style='text-align: center; color: grey;'>Importar tablas de similitudes</div></html>");
    private JButton saveChangesButton = new JButton("<html><div style='text-align: center; color: green;'>Guardar cambios</div></html>");
    private JPanel contentPanel = new JPanel();

    private ViewPrimary viewPrimary;

    /**
     * Constructor de la clase
     * @param viewPrimary vista principal
     */
    public SimilarityTablesManagePanel(ViewPrimary viewPrimary) {
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

        addSimilarityTableButton.addActionListener(e -> viewPrimary.transitionContentPanel(viewPrimary.getAddSimilarityTablePanel1()));

        addSimilarityTableButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        importSimilarityTablesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveChangesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonPanel.add(addSimilarityTableButton, BorderLayout.WEST);
        buttonPanel.add(importSimilarityTablesButton, BorderLayout.CENTER);
        buttonPanel.add(saveChangesButton, BorderLayout.EAST);


        importSimilarityTablesButton.addActionListener(e -> {
            try {
                CtrlPresentation.getInstance().importSimilarityTables();
                updateList();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        new JFrame(),
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        saveChangesButton.addActionListener(e -> {
            try {
                CtrlPresentation.getInstance().saveSimilarityTables();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        new JFrame(),
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Inicializa la lista de tablas de similitud
     * @param ids lista de ids de las tablas de similitud
     */
    private void initializeList(String[] ids) {
        JComponent[] data = new JComponent[ids.length];

        for (int i = 0; i < ids.length; i++) {
            data[i] = new JLabel(ids[i], SwingConstants.CENTER);
        }

        String[] cols = CtrlPresentation.getInstance().getSimilarityTablesCols();
        itemListSimilarityTables = new ItemListSimilarityTables(viewPrimary, this, ids.length, cols.length, cols, data);
    }

    /**
     * Actualiza la lista de tablas de similitud
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

        String[] dataPresentation = CtrlPresentation.getInstance().getSimilarityTables();

        if (dataPresentation.length > 0) {
            initializeList(dataPresentation);
            contentPanel.add(itemListSimilarityTables, BorderLayout.CENTER);
        } else {
            JLabel noSimilarityTablesTitle = new JLabel("No hay tablas de similitud en el sistema", SwingConstants.CENTER);
            noSimilarityTablesTitle.setFont(new Font("Arial", Font.BOLD, 24));
            contentPanel.add(noSimilarityTablesTitle, BorderLayout.CENTER);
        }

        JPanel buttonPanel = new JPanel(new BorderLayout(10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(addSimilarityTableButton, BorderLayout.WEST);
        buttonPanel.add(importSimilarityTablesButton, BorderLayout.CENTER);
        buttonPanel.add(saveChangesButton, BorderLayout.EAST);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}
