package presentation.components;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemListProducts extends JPanel {
    private ViewPrimary viewPrimary;
    int rows;
    int columns;
    String[] columnsTitles;
    JComponent[][] data;

    public ItemListProducts(ViewPrimary viewPrimary, int glRow, int glColumn, String[] columnsTitles, JComponent[][] data) {
        this.viewPrimary = viewPrimary;
        this.rows = glRow;
        this.columns = glColumn;
        this.columnsTitles = columnsTitles;
        this.data = data;
        initialize();
    }

    private void initialize() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Dimension sizes = new Dimension(80, 20);

        //Contenedor de titulos
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

        //Contenedores de filas
        for (int row = 0; row < rows; row++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
            rowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JPanel productPanel = new JPanel(new GridLayout(1, columns + 1, 0, 0));
            productPanel.setBackground(Color.LIGHT_GRAY);

            JLabel rowLabel = new JLabel("" + (row + 1), SwingConstants.CENTER);
            rowLabel.setPreferredSize(sizes);
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
                    productPanel.setBackground(Color.CYAN);
                    // Creates a new view
                    viewPrimary.transitionContentPanel(viewPrimary.getProductPanel());

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    productPanel.setBackground(Color.LIGHT_GRAY);
                }
            });

            JButton deleteButton = new JButton("<html><div style='text-align: center; color: red;'>delete</div></html>");
            deleteButton.setPreferredSize(new Dimension(90, 20));
            deleteButton.setMinimumSize(new Dimension(90, 20));
            deleteButton.setMaximumSize(new Dimension(90, 20));

            String id = data[row][0].toString();
            deleteButton.addActionListener(e -> CtrlPresentation.getInstance().deleteProductById(id));

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
