package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.components.ItemListSimilarityTables;
import presentation.components.ProductsListSTLeft;
import presentation.components.STListDistributionLeft;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

public class GenerateDistributionPanel extends JPanel {
    private STListDistributionLeft itemListSTLeft;
    private JPanel selectOptionsPanel;

    private JPanel stPanel;

    private JLabel title = new JLabel("Generar Distribucion", SwingConstants.CENTER);

    private JButton submitButton = new JButton("Generar");

    private ViewPrimary viewPrimary;

    public GenerateDistributionPanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        initializeComponents();
    }

    protected void initializeComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        stPanel = new JPanel(new BorderLayout(5, 5));
        stPanel.setBorder(BorderFactory.createTitledBorder("Tablas de similitudes disponibles"));

        String[] dataPresentation = CtrlPresentation.getInstance().getSimilarityTables();

        if (dataPresentation.length > 0) {
            initializeListLeft(dataPresentation);
        } else {
            initializeListLeft(new String[0]);
        }
        stPanel.add(itemListSTLeft, BorderLayout.CENTER);

        selectOptionsPanel = new JPanel(new BorderLayout(5, 5));
        selectOptionsPanel.setBorder(BorderFactory.createTitledBorder("Opciones"));


        listsPanel.add(stPanel);
        listsPanel.add(selectOptionsPanel);

        JPanel buttonPanel = new JPanel();
        submitButton.setText("Siguiente");
        submitButton.setPreferredSize(new Dimension(120, 30));
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(submitButton);

        add(title, BorderLayout.NORTH);
        add(listsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> handleSubmit());
    }


    private void handleSubmit() {
        // TODO: Only if selected st

        /*
        if (selectedProducts.length == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, seleccione al menos un producto antes de continuar.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }*/

        // TODO: Call CtlPresentation add Distribution
        //viewPrimary.transitionContentPanel(viewPrimary.getAddSimilarityTablePanel2(selectedProducts));

    }

    private void initializeListLeft(String[] ids) {
        JComponent[] data = new JComponent[ids.length];

        for (int i = 0; i < ids.length; i++) {
            data[i] = new JLabel(ids[i], SwingConstants.CENTER);
        }

        String[] cols = CtrlPresentation.getInstance().getSimilarityTablesCols();
        itemListSTLeft = new STListDistributionLeft(viewPrimary, this, ids.length, cols.length, cols, data, "No hay tablas de similitudes disponibles");
    }

    private void initializeListRight() {
        // Simple panel with options
    }
}
