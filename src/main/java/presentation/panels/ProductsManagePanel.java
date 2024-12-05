package presentation.panels;

import presentation.components.ItemListProducts;
import presentation.views.ViewPrimary;

import javax.swing.*;

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
        JComponent[][] data = {{new JLabel("one"), new JLabel("two")},
                {new JLabel("three"), new JLabel("four")}};
        String[] cols = {"one", "two"};
        itemListProducts = new ItemListProducts(viewPrimary, 2, 2, cols, data);

        this.add(itemListProducts);
    }
}
