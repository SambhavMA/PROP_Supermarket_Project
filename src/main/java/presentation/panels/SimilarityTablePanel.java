package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class SimilarityTablePanel extends JPanel {
    private JLabel title = new JLabel("Modificar Producto", SwingConstants.CENTER);
    private JLabel nameLabel = new JLabel("Nombre:");
    private JLabel nameDisplay = new JLabel(); // JLabel para mostrar el nombre
    private String name;
    private String type;
    private JLabel typeLabel = new JLabel("Tipo:");
    private JTextField typeInput = new JTextField(20);
    private JButton submitButton = new JButton("Guardar cambios");

    private ViewPrimary viewPrimary;

    public SimilarityTablePanel(ViewPrimary viewPrimary, int id) {
        this.viewPrimary = viewPrimary;
        this.name = name;
        this.type = type;
        initializeComponents();
    }

    protected void initializeComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        nameDisplay.setText(name);
        nameDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
        add(nameDisplay, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(typeLabel, gbc);
        gbc.gridx = 1;
        typeInput.setText(type);
        add(typeInput, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        submitButton.setPreferredSize(new Dimension(120, 30));
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setEnabled(false);
        add(submitButton, gbc);

        typeInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleSubmitButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleSubmitButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleSubmitButton();
            }

            private void toggleSubmitButton() {
                submitButton.setEnabled(!typeInput.getText().trim().equals(type));
            }
        });

        submitButton.addActionListener(e -> handleSubmit());
    }

    private void handleSubmit() {
        String type = typeInput.getText().trim();

        if (type.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, complete el campo tipo antes de guardar los cambios.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String errorMessage = CtrlPresentation.getInstance().modifyProduct(name, type);
        if (errorMessage == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "El producto \"" + name + "\" ha sido modificado exitosamente.",
                    "Modificación completada",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al modificar el producto: " + errorMessage,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        viewPrimary.transitionContentPanel(viewPrimary.getProductsManagePanel());
    }
}
