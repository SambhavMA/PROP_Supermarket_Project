package presentation.panels;

import presentation.components.ItemList;

import javax.swing.*;
import java.awt.*;

public class ProductsManagePanel extends JPanel {
    ItemList itemList;

    public ProductsManagePanel() {
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
        itemList = new ItemList(ViewSecundaryPanelsEnum.PRODUCTPANEL, 2, 2, cols, data);

        this.add(itemList);
    }
}
