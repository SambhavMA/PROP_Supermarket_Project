package presentation.components;

import controller.presentation.CtrlPresentation;
import presentation.panels.ProductsManagePanel;
import presentation.panels.SimilarityTablePanel;
import presentation.panels.SimilarityTablesManagePanel;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemListSimilarityTables extends JPanel {
    private ViewPrimary viewPrimary;
    private SimilarityTablesManagePanel parentPanel;
    int rows;
    int columns;
    String[] columnsTitles;
    JComponent[] data;

    public ItemListSimilarityTables(ViewPrimary viewPrimary, SimilarityTablesManagePanel parentPanel, int glRow, int glColumn, String[] columnsTitles, JComponent[] data) {
        this.viewPrimary = viewPrimary;
        this.parentPanel = parentPanel;
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

            JPanel stPanel = new JPanel(new GridLayout(1, columns + 1, 0, 0));
            stPanel.setBackground(Color.LIGHT_GRAY);
            stPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

            data[row].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            data[row].setPreferredSize(sizes);
            stPanel.add(data[row]);

            stPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int id = Integer.parseInt(((JLabel) data[currentRow]).getText());
                    viewPrimary.transitionContentPanel(viewPrimary.getSimilarityTablePanel(id));
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    stPanel.setBackground(Color.CYAN);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    stPanel.setBackground(Color.LIGHT_GRAY);
                }
            });

            JButton deleteButton = new JButton("<html><div style='text-align: center; color: red;'>Eliminar</div></html>");
            deleteButton.setPreferredSize(new Dimension(90, 25));
            deleteButton.setMinimumSize(new Dimension(90, 25));
            deleteButton.setMaximumSize(new Dimension(90, 25));
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            deleteButton.addActionListener(e -> {
                int stID = Integer.parseInt(((JLabel) data[currentRow]).getText());

                int response = JOptionPane.showConfirmDialog(
                        this,
                        "¿Estás seguro de que deseas eliminar la tabla de similitud \"" + stID + "\"?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (response == JOptionPane.YES_OPTION) {
                    String errorMessage = CtrlPresentation.getInstance().deleteSimilarityTable(stID);
                    parentPanel.updateList();
                    if (errorMessage == null) {
                        JOptionPane.showMessageDialog(
                                this,
                                "La tabla de similitud \"" + stID + "\" ha sido eliminada exitosamente.",
                                "Eliminación completada",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Error al eliminar la tabla " + errorMessage,
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            });



            rowPanel.add(stPanel);
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
