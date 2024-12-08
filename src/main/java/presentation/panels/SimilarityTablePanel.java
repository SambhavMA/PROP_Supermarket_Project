package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SimilarityTablePanel extends JPanel {
    private JLabel title = new JLabel("Modificar Tabla de Similitud", SwingConstants.CENTER);
    private JTable similarityTable;
    private String[] products;

    private JButton saveButton = new JButton("Guardar Relaciones");
    private ViewPrimary viewPrimary;

    public SimilarityTablePanel(ViewPrimary viewPrimary, int id) {
        this.viewPrimary = viewPrimary;
        initializeComponents();
    }

    protected void initializeComponents() {
        setLayout(new BorderLayout(10, 10));

        // Título
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Crear el modelo de la tabla
        SimilarityTableModel tableModel = new SimilarityTableModel(products);
        similarityTable = new JTable(tableModel);

        // Configurar renderizador y editor para celdas de la matriz
        similarityTable.setDefaultRenderer(Object.class, new SimilarityCellRenderer());

        // Crear un row header para la columna izquierda
        JList<String> rowHeader = new JList<>(products);
        rowHeader.setFixedCellWidth(150);
        rowHeader.setFixedCellHeight(similarityTable.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(similarityTable));

        JScrollPane scrollPane = new JScrollPane(similarityTable);
        scrollPane.setRowHeaderView(rowHeader);
        add(scrollPane, BorderLayout.CENTER);

        // Botón de guardar relaciones
        saveButton.setPreferredSize(new Dimension(150, 30));
        saveButton.addActionListener(e -> handleSaveRelations());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleSaveRelations() {
        // Obtener los valores de la matriz
        SimilarityTableModel model = (SimilarityTableModel) similarityTable.getModel();
        float[][] relations = model.getRelations();

        // Crear el array de productos y similitudes
        ArrayList<String> similitudesList = new ArrayList<>();
        for (int i = 0; i < relations.length; i++) {
            for (int j = 0; j < relations[i].length; j++) {
                if (i > j && relations[i][j] != 0) { // Solo considerar la mitad inferior
                    similitudesList.add(products[i] + " " + products[j] + " " + relations[i][j]);
                }
            }
        }

        String[] similitudesP = similitudesList.toArray(new String[0]);

        // Llamar a la función del controlador
        // CtrlPresentation.getInstance().guardarRelaciones(products, similitudesP);

        JOptionPane.showMessageDialog(this, "Relaciones guardadas correctamente.");
    }

    // Modelo personalizado para la tabla
    private static class SimilarityTableModel extends AbstractTableModel {
        private String[] products;
        private float[][] relations;

        public SimilarityTableModel(String[] products) {
            this.products = products;
            this.relations = new float[products.length][products.length];

            // Inicializar relaciones con 0 por defecto
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
                return "—"; // Diagonal principal no editable
            }
            return relations[rowIndex][columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return rowIndex > columnIndex; // Solo editable en la mitad inferior
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

    // Renderizador de celdas personalizado
    private static class SimilarityCellRenderer extends JLabel implements TableCellRenderer {
        public SimilarityCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            if (row == column) {
                setBackground(Color.LIGHT_GRAY); // Diagonal principal
            } else if (row > column) {
                setBackground(Color.WHITE); // Editables
            } else {
                setBackground(Color.GRAY); // No editables
            }
            if (isSelected) {
                setBackground(Color.CYAN);
            }
            return this;
        }
    }

    // Renderizador para la columna izquierda
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
