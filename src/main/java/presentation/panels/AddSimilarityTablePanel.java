package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;

public class AddSimilarityTablePanel extends JPanel {
    private int id = CtrlPresentation.getInstance().getSimilarityTableNextId();

    private JLabel title = new JLabel("Añadir Tabla de Similitud", SwingConstants.CENTER);
    private JLabel nameLabel = new JLabel("id de la tabla: " + id);

    private JLabel productsInfo = new JLabel(
            "<html>Formato correcto: una vez cada producto<br>" +
                    "Con un salto de linea entre productos.<br>" +
                    "Inserte los productos de la tabla de similitud:</html>"
    );
    private JTextArea productsInput = new JTextArea();

    private JLabel relationsInfo = new JLabel(
            "<html>Formato correcto: producto1 producto2 relacion<br>" +
                    "Con un salto de linea entre relaciones.<br>" +
                    "Inserte las relaciones de la tabla de similitud:</html>"
    );
    private JTextArea relationsInput = new JTextArea();

    private JButton submitButton = new JButton("Añadir");

    private ViewPrimary viewPrimary;

    public AddSimilarityTablePanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        initializeComponents();
    }

    protected void initializeComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, gbc);

        // Name Label
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(nameLabel, gbc);

        // Products Info with fixed size
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.1;

        JPanel productsInfoPanel = new JPanel(new BorderLayout());
        productsInfoPanel.setPreferredSize(new Dimension(200, 60));
        productsInfoPanel.add(productsInfo, BorderLayout.CENTER);
        add(productsInfoPanel, gbc);

        // Products Input
        gbc.gridy = 3;
        gbc.weighty = 0.5;
        JScrollPane productsScrollPane = new JScrollPane(productsInput);
        productsScrollPane.setPreferredSize(new Dimension(200, 100));
        add(productsScrollPane, gbc);

        // Relations Info with fixed size
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.1;

        JPanel relationsInfoPanel = new JPanel(new BorderLayout());
        relationsInfoPanel.setPreferredSize(new Dimension(200, 60));
        relationsInfoPanel.add(relationsInfo, BorderLayout.CENTER);
        add(relationsInfoPanel, gbc);

        // Relations Input
        gbc.gridy = 3;
        gbc.weighty = 0.5;
        JScrollPane relationsScrollPane = new JScrollPane(relationsInput);
        relationsScrollPane.setPreferredSize(new Dimension(200, 100));
        add(relationsScrollPane, gbc);

        // Submit Button
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        submitButton.setPreferredSize(new Dimension(120, 30));
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(submitButton, gbc);

        // Submit Button Action
        submitButton.addActionListener(e -> handleSubmit());
    }

    private void handleSubmit() {
        String[] products = productsInput.getText().trim().split("\n");
        String[] relations = relationsInput.getText().trim().split("\n");

        if (products.length == 0 || relations.length == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, complete ambos campos antes de añadir la tabla de similitud.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String errorMessage = CtrlPresentation.getInstance().addSimilarityTable(products,relations);
        if (errorMessage == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "La tabla de similitud con id: " + id + " se ha añadido correctamente.",
                    "Añadido correctamente",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al añadir la tabla de similitud: " + errorMessage,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        productsInput.setText("");
        relationsInput.setText("");

        viewPrimary.transitionContentPanel(viewPrimary.getSimilarityTablesManagePanel());
    }
}
