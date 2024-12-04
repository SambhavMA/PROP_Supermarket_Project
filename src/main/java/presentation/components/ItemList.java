package presentation.components;

import controller.presentation.CtrlPresentation;
import presentation.panels.ViewSecundaryPanelsEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemList extends JPanel {

    int rows;
    int columns;
    String[] columnsTitles;
    Component[][] data;
    ViewSecundaryPanelsEnum typePanel;

    public ItemList(ViewSecundaryPanelsEnum typePanel, int glRow, int glColumn, String[] columnsTitles, Component[][] data) {
        this.typePanel = typePanel;
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

        //Contenedor de titulos
        JPanel headerPanel = new JPanel(new GridLayout( 1, columns + 1));
        headerPanel.add(new JLabel(""));
        for (int col = 0; col < columns; col++) {
            JLabel columnLabel = new JLabel(columnsTitles[col], SwingConstants.CENTER);
            headerPanel.add(columnLabel);
        }
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        containerPanel.add(headerPanel);

        //Contenedores de filas
        for (int row = 0; row < rows; row++) {
            JPanel rowPanel = new JPanel(new GridLayout(1, columns + 1, 10, 0)); // GridLayout for row elements
            rowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional border
            rowPanel.setBackground(Color.LIGHT_GRAY);

            JLabel rowLabel = new JLabel("" + (row + 1), SwingConstants.CENTER);
            rowPanel.add(rowLabel);
            for (int col = 0; col < columns; col++) {
                rowPanel.add(data[row][col]);
            }

            rowPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    rowPanel.setBackground(Color.CYAN);
                    // Creates a new view

                    CtrlPresentation.getInstance().transitionPrimary_to_Secundary(typePanel);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    rowPanel.setBackground(Color.LIGHT_GRAY);
                }
            });

            containerPanel.add(rowPanel);
        }

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

}
