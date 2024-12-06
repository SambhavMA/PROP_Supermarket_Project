package presentation.panels;

import controller.presentation.CtrlPresentation;
import model.exceptions.ProductNotFoundException;
import presentation.components.ItemListProducts;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

public class ProductsManagePanel extends JPanel {
    private ItemListProducts itemListProducts;
    private JButton addProductButton = new JButton("<html><div style='text-align: center; color: grey;'>Añadir producto</div></html>");
    private JButton importProductsButton = new JButton("<html><div style='text-align: center; color: grey;'>Importar productos</div></html>");
    private JButton saveChangesButton = new JButton("<html><div style='text-align: center; color: green;'>Guardar cambios</div></html>");
    private JPanel contentPanel = new JPanel();

    private ViewPrimary viewPrimary;

    public ProductsManagePanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        compose();

    }

    private void compose() {
        Pair<String, String>[] dataPresentation = CtrlPresentation.getInstance().getProducts();
        if (dataPresentation.length > 0) {
            initializeList(dataPresentation);
            initializeButtons();

            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(itemListProducts, BorderLayout.CENTER);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout(10,10));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            buttonPanel.add(addProductButton, BorderLayout.WEST);
            buttonPanel.add(importProductsButton, BorderLayout.CENTER);
            buttonPanel.add(saveChangesButton, BorderLayout.EAST);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);
            this.add(contentPanel, BorderLayout.CENTER);

        } else {
            JLabel noProductsTitle = new JLabel("No hay productos en el sistema");

            setLayout(new GridBagLayout());

            noProductsTitle.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.add(noProductsTitle);

            add(textPanel);
        }
    }

    private void initializeButtons() {

    }

    private void initializeList(Pair<String, String>[] dataP) {
        JComponent[][] data = new JComponent[dataP.length][2];

        for (int i = 0; i < data.length; i++) {
            data[i][0] = new JLabel(dataP[i].first);
            data[i][1] = new JLabel(dataP[i].second);
        }

        String[] cols = CtrlPresentation.getInstance().getProductsCols();
        itemListProducts = new ItemListProducts(viewPrimary, 2, 2, cols, data);
    }
}
