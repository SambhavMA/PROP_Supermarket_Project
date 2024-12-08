package presentation.components;

import presentation.panels.AddSimilarityTablePanel1;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductsListSTLeft extends JPanel {
    private ViewPrimary viewPrimary;
    private AddSimilarityTablePanel1 parentPanel;
    private int rows;
    private int columns;
    private String[] columnsTitles;
    private JComponent[][] data;

    private JPanel containerPanel;
    private JScrollPane scrollPane;
    private String emptyMessage;

    public ProductsListSTLeft(ViewPrimary viewPrimary, AddSimilarityTablePanel1 parentPanel, int glRow, int glColumn, String[] columnsTitles, JComponent[][] data, String emptyMessage) {
        this.viewPrimary = viewPrimary;
        this.parentPanel = parentPanel;
        this.rows = glRow;
        this.columns = glColumn;
        this.columnsTitles = columnsTitles;
        this.data = data;
        this.emptyMessage = emptyMessage;
        initialize();
    }

    private void initialize() {
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (rows == 0) {
            showEmptyMessage();
        } else {
            Dimension sizes = new Dimension(80, 25);
            for (int row = 0; row < rows; row++) {
                addRowToContainer(row, sizes);
            }
        }

        scrollPane = new JScrollPane(containerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addRowToContainer(int row, Dimension sizes) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel productPanel = new JPanel(new GridLayout(1, columns + 1, 0, 0));
        productPanel.setBackground(Color.LIGHT_GRAY);
        productPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        for (int col = 0; col < columns; col++) {
            data[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            data[row][col].setPreferredSize(sizes);
            productPanel.add(data[row][col]);
        }

        productPanel.addMouseListener(new MouseAdapter() {
            private final int currentIndex = row;

            @Override
            public void mouseClicked(MouseEvent e) {
                String[] removedProduct = removeProduct(currentIndex);
                parentPanel.handleRowClick(removedProduct, ProductsListSTLeft.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                productPanel.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                productPanel.setBackground(Color.LIGHT_GRAY);
            }
        });

        rowPanel.add(productPanel);
        containerPanel.add(rowPanel);
    }

    private void showEmptyMessage() {
        containerPanel.removeAll();
        JLabel emptyLabel = new JLabel(emptyMessage, SwingConstants.CENTER);
        emptyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        containerPanel.add(emptyLabel);
    }

    public void addProduct(String[] productData) {
        if (productData.length != columns) {
            throw new IllegalArgumentException("El número de datos no coincide con las columnas.");
        }

        if (rows == 0) {
            containerPanel.removeAll();
        }

        JComponent[] newRow = new JComponent[columns];
        for (int i = 0; i < columns; i++) {
            newRow[i] = new JLabel(productData[i]);
        }

        JComponent[][] newData = new JComponent[rows + 1][columns];
        System.arraycopy(data, 0, newData, 0, rows);
        newData[rows] = newRow;
        data = newData;
        rows++;

        addRowToContainer(rows - 1, new Dimension(80, 25));

        refreshRowListeners();

        containerPanel.revalidate();
        containerPanel.repaint();
    }

    private void refreshRowListeners() {
        Component[] rows = containerPanel.getComponents();
        for (int i = 0; i < rows.length; i++) {
            JPanel rowPanel = (JPanel) rows[i];
            JPanel productPanel = (JPanel) rowPanel.getComponent(0);

            // Remover todos los listeners previos
            for (MouseListener listener : productPanel.getMouseListeners()) {
                productPanel.removeMouseListener(listener);
            }

            final int updatedIndex = i;
            productPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String[] removedProduct = removeProduct(updatedIndex);
                    parentPanel.handleRowClick(removedProduct, ProductsListSTLeft.this);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    productPanel.setBackground(Color.CYAN);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    productPanel.setBackground(Color.LIGHT_GRAY);
                }
            });
        }
    }


    public String[] removeProduct(int index) {
        if (index < 0 || index >= rows) {
            throw new IllegalArgumentException("Índice fuera de rango.");
        }

        String[] removedProduct = new String[columns];
        for (int i = 0; i < columns; i++) {
            removedProduct[i] = ((JLabel) data[index][i]).getText();
        }

        containerPanel.remove(index);

        JComponent[][] newData = new JComponent[rows - 1][columns];
        for (int i = 0, j = 0; i < rows; i++) {
            if (i != index) {
                newData[j++] = data[i];
            }
        }
        data = newData;
        rows--;

        if (rows == 0) {
            showEmptyMessage();
        } else {
            refreshRowListeners();
        }

        containerPanel.revalidate();
        containerPanel.repaint();

        return removedProduct;
    }

    public String[] getAllProducts() {
        String[] allProducts = new String[rows];
        for (int i = 0; i < rows; i++) {
            allProducts[i] = ((JLabel) data[i][0]).getText(); // Obtiene solo el nombre del producto (columna 0)
        }
        return allProducts;
    }

}



