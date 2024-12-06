package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.components.ItemListProducts;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

public class ProductsManagePanel extends JPanel {
    ItemListProducts itemListProducts;
    private JLabel noProducts = new JLabel("NO HAY PRODUCTOS EN EL SISTEMA", SwingConstants.CENTER);
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

        JComponent[][] data = processData();
        if (data != null) {
            String[] cols = CtrlPresentation.getInstance().getProductsCols();
            itemListProducts = new ItemListProducts(viewPrimary, 2, 2, cols, data);

            this.add(itemListProducts);

        } else {
            setLayout(new GridBagLayout());

            noProducts.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.add(noProducts);

            add(textPanel);
        }

    }

    private JComponent[][] processData() {
        Pair<String, String>[] data = CtrlPresentation.getInstance().getProducts();
        if (data == null) return null;

        JComponent[][] dataReturn = new JComponent[data.length][2];

        for (int i = 0; i < data.length; i++) {
            dataReturn[i][0] = new JLabel(data[i].first);
            dataReturn[i][1] = new JLabel(data[i].second);
        }

        return dataReturn;
    }
}
