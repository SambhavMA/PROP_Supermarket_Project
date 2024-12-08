package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AddSimilarityTablePanel2 extends JPanel {
    private ViewPrimary viewPrimary;
    private JTable similarityTable;
    private String[] selectedProducts;

    public AddSimilarityTablePanel2(ViewPrimary viewPrimary, String[] selectedProducts) {
        this.viewPrimary = viewPrimary;
        this.selectedProducts = selectedProducts;
        initializeComponents();
    }

    protected void initializeComponents() {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Definir Relaciones de Similitud", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        SimilarityTableModel tableModel = new SimilarityTableModel(selectedProducts);
        similarityTable = new JTable(tableModel);

        similarityTable.setDefaultRenderer(Object.class, new SimilarityCellRenderer());

        JList<String> rowHeader = new JList<>(selectedProducts);
        rowHeader.setFixedCellWidth(150);
        rowHeader.setFixedCellHeight(similarityTable.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(similarityTable));
        JScrollPane scrollPane = new JScrollPane(similarityTable);
        scrollPane.setRowHeaderView(rowHeader);

        add(scrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Guardar Tabla");
        saveButton.addActionListener(e -> handleSaveRelations());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleSaveRelations() {
        SimilarityTableModel model = (SimilarityTableModel) similarityTable.getModel();
        float[][] relations = model.getRelations();

        String[] productosP = selectedProducts;
        ArrayList<String> similitudesList = new ArrayList<>();

        for (int i = 0; i < relations.length; i++) {
            for (int j = 0; j < relations[i].length; j++) {
                if (i > j && relations[i][j] != 0) {
                    similitudesList.add(productosP[i] + " " + productosP[j] + " " + relations[i][j]);
                }
            }
        }

        String[] similitudesP = similitudesList.toArray(new String[0]);

        CtrlPresentation.getInstance().addSimilarityTable(productosP, similitudesP);

        JOptionPane.showMessageDialog(this, "Tabla de Similitud añadida correctamente.");
        viewPrimary.transitionContentPanel(viewPrimary.getSimilarityTablesManagePanel());
    }

    private static class SimilarityTableModel extends AbstractTableModel {
        private String[] products;
        private float[][] relations;

        public SimilarityTableModel(String[] products) {
            this.products = products;
            this.relations = new float[products.length][products.length];

            for (int i = 0; i < relations.length; i++) {
                Arrays.fill(relations[i], 0);
            }
        }

        @Override
        public int getRowCount() {
            return products.length;
        }

        @Override
        public int getColumnCount() {
            return products.length;
        }

        @Override
        public String getColumnName(int column) {
            return products[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex == columnIndex) {
                return "—";
            }
            return relations[rowIndex][columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return rowIndex > columnIndex;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (rowIndex > columnIndex) {
                try {
                    float value = Float.parseFloat(aValue.toString());
                    if (value < 0 || value > 1) {
                        JOptionPane.showMessageDialog(null,
                                "Por favor, ingrese un valor en el rango [0, 1].",
                                "Valor fuera de rango",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    relations[rowIndex][columnIndex] = value;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, ingrese un valor numérico válido.",
                            "Error de formato",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }


        public float[][] getRelations() {
            return relations;
        }
    }

    private static class SimilarityCellRenderer extends JLabel implements TableCellRenderer {
        public SimilarityCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            if (row == column) {
                setBackground(Color.LIGHT_GRAY);
            } else if (row > column) {
                setBackground(Color.WHITE);
            } else {
                setBackground(Color.GRAY);
            }
            return this;
        }
    }

    private static class RowHeaderRenderer extends JLabel implements ListCellRenderer<String> {
        public RowHeaderRenderer(JTable table) {
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(table.getTableHeader().getFont());
            setBackground(Color.LIGHT_GRAY);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);
            return this;
        }
    }
}
