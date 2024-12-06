package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;

public class AddProductPanel extends JPanel {
    private JLabel title = new JLabel("Añadir Producto", SwingConstants.CENTER);
    private JLabel nameLabel = new JLabel("Nombre:");
    private JTextField nameInput = new JTextField(20);
    private JLabel typeLabel = new JLabel("Tipo:");
    private JTextField typeInput = new JTextField(20);
    private JButton submitButton = new JButton("Añadir");

    private ViewPrimary viewPrimary;

    public AddProductPanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
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
        add(nameInput, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(typeLabel, gbc);
        gbc.gridx = 1;
        add(typeInput, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        submitButton.setPreferredSize(new Dimension(120, 30));
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(submitButton, gbc);

        submitButton.addActionListener(e -> handleSubmit());
    }

    private void handleSubmit() {
        String name = nameInput.getText().trim();
        String type = typeInput.getText().trim();

        if (name.isEmpty() || type.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, complete ambos campos antes de añadir el producto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        String errorMessage = CtrlPresentation.getInstance().addProduct(name,type);
        if (errorMessage == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "El producto \"" + name + "\" ha sido añadido exitosamente.",
                    "Add completado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al añadir el producto: " + errorMessage,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        nameInput.setText("");
        typeInput.setText("");

        viewPrimary.transitionContentPanel(viewPrimary.getProductsManagePanel());
    }
}
