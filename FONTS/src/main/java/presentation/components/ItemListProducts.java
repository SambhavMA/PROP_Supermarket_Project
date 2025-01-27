package presentation.components;

import controller.presentation.CtrlPresentation;
import presentation.panels.ProductsManagePanel;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel de lista de productos
 */
public class ItemListProducts extends JPanel {
    private ViewPrimary viewPrimary;
    private ProductsManagePanel parentPanel;
    int rows;
    int columns;
    String[] columnsTitles;
    JComponent[][] data;

    /**
     * Constructor de la clase
     * @param viewPrimary vista principal
     * @param parentPanel panel padre
     * @param glRow numero de filas
     * @param glColumn numero de columnas
     * @param columnsTitles titulos de las columnas
     * @param data lista de productos
     */
    public ItemListProducts(ViewPrimary viewPrimary, ProductsManagePanel parentPanel, int glRow, int glColumn, String[] columnsTitles, JComponent[][] data) {
        this.viewPrimary = viewPrimary;
        this.parentPanel = parentPanel;
        this.rows = glRow;
        this.columns = glColumn;
        this.columnsTitles = columnsTitles;
        this.data = data;
        initialize();
    }

    /**
     * Inicializa los componentes del panel
     */
    private void initialize() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Dimension sizes = new Dimension(80, 25);

        // contenedor de titulos
        JPanel headerPanel = new JPanel(new GridLayout( 1, columns + 1, 0, 0));
        JLabel blank = new JLabel("");
        blank.setPreferredSize(sizes);
        headerPanel.add(blank);
        for (int col = 0; col < columns; col++) {
            JLabel columnLabel = new JLabel(columnsTitles[col], SwingConstants.CENTER);
            columnLabel.setPreferredSize(sizes);
            headerPanel.add(columnLabel);
        }
        JLabel blank2 = new JLabel("");
        blank2.setPreferredSize(sizes);
        headerPanel.add(blank2);

        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        containerPanel.add(headerPanel, BorderLayout.NORTH);

        // contenedores de filas
        for (int row = 0; row < rows; row++) {
            final int currentRow = row;
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
            rowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JPanel productPanel = new JPanel(new GridLayout(1, columns + 1, 0, 0));
            productPanel.setBackground(Color.LIGHT_GRAY);
            productPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JLabel rowLabel = new JLabel("" + (row + 1), SwingConstants.CENTER);
            rowLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            productPanel.add(rowLabel);

            for (int col = 0; col < columns; col++) {
                data[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                data[row][col].setPreferredSize(sizes);
                productPanel.add(data[row][col]);
            }

            productPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String name = ((JLabel) data[currentRow][0]).getText();
                    String type = ((JLabel) data[currentRow][1]).getText();
                    viewPrimary.transitionContentPanel(viewPrimary.getProductPanel(name, type));
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

            JButton deleteButton = new JButton("<html><div style='text-align: center; color: red;'>Eliminar</div></html>");
            deleteButton.setPreferredSize(new Dimension(90, 25));
            deleteButton.setMinimumSize(new Dimension(90, 25));
            deleteButton.setMaximumSize(new Dimension(90, 25));
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            deleteButton.addActionListener(e -> {
                String productName = ((JLabel) data[currentRow][0]).getText();

                int response = JOptionPane.showConfirmDialog(
                        this,
                        "¿Estás seguro de que deseas eliminar el producto \"" + productName + "\"?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (response == JOptionPane.YES_OPTION) {
                    CtrlPresentation.getInstance().deleteProductById(productName);
                    parentPanel.updateList();
                    JOptionPane.showMessageDialog(
                            this,
                            "El producto \"" + productName + "\" ha sido eliminado exitosamente.",
                            "Eliminación completada",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });



            rowPanel.add(productPanel);
            rowPanel.add(deleteButton);

            containerPanel.add(rowPanel);
        }

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

}
