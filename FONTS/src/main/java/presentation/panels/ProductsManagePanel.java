package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.components.ItemListProducts;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de gestion de productos
 */
public class ProductsManagePanel extends JPanel {
    private ItemListProducts itemListProducts;
    private JButton addProductButton = new JButton("<html><div style='text-align: center; color: grey;'>Añadir producto</div></html>");
    private JButton importProductsButton = new JButton("<html><div style='text-align: center; color: grey;'>Importar productos</div></html>");
    private JButton saveChangesButton = new JButton("<html><div style='text-align: center; color: green;'>Guardar cambios</div></html>");
    private JPanel contentPanel = new JPanel();

    private ViewPrimary viewPrimary;

    /**
     * Constructor de la clase
     * @param viewPrimary vista principal
     */
    public ProductsManagePanel(ViewPrimary viewPrimary) {
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

        addProductButton.addActionListener(e -> viewPrimary.transitionContentPanel(viewPrimary.getAddProductPanel()));

        addProductButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        importProductsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveChangesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonPanel.add(addProductButton, BorderLayout.WEST);
        buttonPanel.add(importProductsButton, BorderLayout.CENTER);
        buttonPanel.add(saveChangesButton, BorderLayout.EAST);


        importProductsButton.addActionListener(e -> {
            try {
                CtrlPresentation.getInstance().importProducts();
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
                CtrlPresentation.getInstance().saveProducts();
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
     * Inicializa la lista de productos
     * @param dataP datos de los productos
     */
    private void initializeList(Pair<String, String>[] dataP) {
        JComponent[][] data = new JComponent[dataP.length][2];

        for (int i = 0; i < data.length; i++) {
            data[i][0] = new JLabel(dataP[i].first);
            data[i][1] = new JLabel(dataP[i].second);
        }

        String[] cols = CtrlPresentation.getInstance().getProductsCols();
        itemListProducts = new ItemListProducts(viewPrimary, this, dataP.length, cols.length, cols, data);
    }

    /**
     * Actualiza la lista de productos
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

        Pair<String, String>[] dataPresentation = CtrlPresentation.getInstance().getProducts();

        if (dataPresentation.length > 0) {
            initializeList(dataPresentation);
            contentPanel.add(itemListProducts, BorderLayout.CENTER);
        } else {
            JLabel noProductsTitle = new JLabel("No hay productos en el sistema", SwingConstants.CENTER);
            noProductsTitle.setFont(new Font("Arial", Font.BOLD, 24));
            contentPanel.add(noProductsTitle, BorderLayout.CENTER);
        }

        JPanel buttonPanel = new JPanel(new BorderLayout(10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(addProductButton, BorderLayout.WEST);
        buttonPanel.add(importProductsButton, BorderLayout.CENTER);
        buttonPanel.add(saveChangesButton, BorderLayout.EAST);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}
