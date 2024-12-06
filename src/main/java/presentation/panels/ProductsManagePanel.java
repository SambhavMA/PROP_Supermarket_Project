package presentation.panels;

import controller.presentation.CtrlPresentation;
import model.exceptions.ProductNotFoundException;
import presentation.components.ItemListProducts;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

public class ProductsManagePanel extends JPanel {
    ItemListProducts itemListProducts;
    private ViewPrimary viewPrimary;

    public ProductsManagePanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        initializeComponents();
    }

    /*
    protected void initializeComponents() {
        setLayout(new GridBagLayout());

        title.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.add(title);

        add(textPanel);
    }*/

    protected void initializeComponents() {
        initializeList();
    }

    private void initializeList() {
        Pair<String, String>[] dataPresentation = CtrlPresentation.getInstance().getProducts();
        if (dataPresentation.length > 0) {
            JComponent[][] data = new JComponent[dataPresentation.length][2];

            for (int i = 0; i < data.length; i++) {
                data[i][0] = new JLabel(dataPresentation[i].first);
                data[i][1] = new JLabel(dataPresentation[i].second);
            }

            String[] cols = CtrlPresentation.getInstance().getProductsCols();
            itemListProducts = new ItemListProducts(viewPrimary, 2, 2, cols, data);

            this.add(itemListProducts);
        } else {
            JLabel noProductsTitle = new JLabel("No hay productos en el sistema");

            setLayout(new GridBagLayout());

            noProductsTitle.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.add(noProductsTitle);

            add(textPanel);
        }
    }
}
