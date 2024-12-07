package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.components.ItemListSimilarityTables;
import presentation.components.ItemListSimilarityTables;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

public class SimilarityTablesManagePanel extends JPanel {
    private ItemListSimilarityTables itemListSimilarityTables;
    private JButton addSimilarityTableButton = new JButton("<html><div style='text-align: center; color: grey;'>Añadir tabla de similitud</div></html>");
    private JButton importSimilarityTablesButton = new JButton("<html><div style='text-align: center; color: grey;'>Importar tablas de similitudes</div></html>");
    private JButton saveChangesButton = new JButton("<html><div style='text-align: center; color: green;'>Guardar cambios</div></html>");
    private JPanel contentPanel = new JPanel();

    private ViewPrimary viewPrimary;

    public SimilarityTablesManagePanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        compose();
    }

    private void compose() {
        setLayout(new BorderLayout());

        contentPanel.setLayout(new BorderLayout());

        refreshContent();

        JPanel buttonPanel = new JPanel(new BorderLayout(10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addSimilarityTableButton.addActionListener(e -> viewPrimary.transitionContentPanel(viewPrimary.getAddSimilarityTablePanel()));

        addSimilarityTableButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        importSimilarityTablesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveChangesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonPanel.add(addSimilarityTableButton, BorderLayout.WEST);
        buttonPanel.add(importSimilarityTablesButton, BorderLayout.CENTER);
        buttonPanel.add(saveChangesButton, BorderLayout.EAST);


        importSimilarityTablesButton.addActionListener(e -> {
            try {
                CtrlPresentation.getInstance().testSimilarityTables();
                updateList();
            } catch (Exception SimilarityTableNotFoundException) {
                SimilarityTableNotFoundException.printStackTrace();
            }
        });

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void initializeList(String[] ids) {
        JComponent[] data = new JComponent[ids.length];

        for (int i = 0; i < ids.length; i++) {
            data[i] = new JLabel(ids[i]);
        }

        String[] cols = CtrlPresentation.getInstance().getSimilarityTablesCols();
        itemListSimilarityTables = new ItemListSimilarityTables(viewPrimary, this, ids.length, cols.length, cols, data);
    }

    public void updateList() {
        refreshContent();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

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
