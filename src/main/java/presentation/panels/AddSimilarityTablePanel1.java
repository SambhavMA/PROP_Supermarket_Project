package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.components.ProductsListSTLeft;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

public class AddSimilarityTablePanel1 extends JPanel {
    private ProductsListSTLeft itemListProductsLeft;
    private ProductsListSTLeft itemListProductsRight;

    private JPanel productsPanel;
    private JPanel selectedPanel;
    private int id = CtrlPresentation.getInstance().getSimilarityTableNextId();

    private JLabel title = new JLabel("Añadir Tabla de Similitud", SwingConstants.CENTER);
    private JLabel nameLabel = new JLabel("id de la tabla: " + id);

    private JTextArea productsInput = new JTextArea();
    private JTextArea relationsInput = new JTextArea();

    private JButton submitButton = new JButton("Añadir");

    private ViewPrimary viewPrimary;

    public AddSimilarityTablePanel1(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        initializeComponents();
    }

    protected void initializeComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        productsPanel = new JPanel(new BorderLayout(5, 5));
        productsPanel.setBorder(BorderFactory.createTitledBorder("Productos disponibles"));

        Pair<String, String>[] dataPresentation = CtrlPresentation.getInstance().getProducts();

        if (dataPresentation.length > 0) {
            initializeListLeft(dataPresentation);
        } else {
            initializeListLeft(new Pair[0]);
        }
        productsPanel.add(itemListProductsLeft, BorderLayout.CENTER);

        selectedPanel = new JPanel(new BorderLayout(5, 5));
        selectedPanel.setBorder(BorderFactory.createTitledBorder("Productos seleccionados"));

        initializeListRight();
        selectedPanel.add(itemListProductsRight, BorderLayout.CENTER);

        listsPanel.add(productsPanel);
        listsPanel.add(selectedPanel);

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


    private void initializeListRight() {
        String[] cols = CtrlPresentation.getInstance().getProductsCols();
        JComponent[][] data = new JComponent[0][cols.length];
        itemListProductsRight = new ProductsListSTLeft(viewPrimary, this, 0, cols.length, cols, data, "No hay productos seleccionados");
    }

    private void handleSubmit() {
        String[] selectedProducts = itemListProductsRight.getAllProducts();

        if (selectedProducts.length == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, seleccione al menos un producto antes de continuar.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        viewPrimary.transitionContentPanel(viewPrimary.getAddSimilarityTablePanel2(selectedProducts));

    }

    private void initializeListLeft(Pair<String, String>[] dataP) {
        JComponent[][] data = new JComponent[dataP.length][2];

        for (int i = 0; i < data.length; i++) {
            data[i][0] = new JLabel(dataP[i].first);
            data[i][1] = new JLabel(dataP[i].second);
        }

        String[] cols = CtrlPresentation.getInstance().getProductsCols();
        itemListProductsLeft = new ProductsListSTLeft(viewPrimary, this, dataP.length, cols.length, cols, data, "No hay productos disponibles");
    }

    public void updateList() {
        Pair<String, String>[] updatedData = CtrlPresentation.getInstance().getProducts();

        productsPanel.removeAll();
        selectedPanel.removeAll();

        if (updatedData.length > 0) {
            initializeListLeft(updatedData);
        } else {
            initializeListLeft(new Pair[0]);
        }

        initializeListRight();

        productsPanel.add(itemListProductsLeft, BorderLayout.CENTER);
        selectedPanel.add(itemListProductsRight, BorderLayout.CENTER);

        productsPanel.revalidate();
        productsPanel.repaint();
        selectedPanel.revalidate();
        selectedPanel.repaint();
    }



    public void handleRowClick(String[] productData, ProductsListSTLeft sourceList) {
        if (productData == null || productData.length == 0) {
            return;
        }

        if (sourceList == itemListProductsLeft) {
            itemListProductsRight.addProduct(productData);
        } else if (sourceList == itemListProductsRight) {
            itemListProductsLeft.addProduct(productData);
        }
    }

}

