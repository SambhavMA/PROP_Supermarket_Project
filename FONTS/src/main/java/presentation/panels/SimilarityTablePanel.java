package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Panel que muestra una tabla de similitud y permite modificarla
 */
public class SimilarityTablePanel extends JPanel {
    private JLabel title = new JLabel("Modificar Tabla de Similitud", SwingConstants.CENTER);
    private JTable similarityTable;
    private String[] products;
    private Pair<Vector<Pair<String, Integer>>, double[][]> st;
    private double[][] similarityMatrix;
    private int id;

    private JButton saveButton = new JButton("Guardar Tabla");
    private ViewPrimary viewPrimary;

    /**
     * Constructor de la clase
     * @param viewPrimary vista principal
     * @param id identificador de la tabla de similitud que se muestra
     */
    public SimilarityTablePanel(ViewPrimary viewPrimary, int id) {
        this.viewPrimary = viewPrimary;
        this.st = CtrlPresentation.getInstance().getSimilarityTable(id);
        this.id = id;

        this.products = this.st.first().stream().map(Pair::first).toArray(String[]::new);
        this.similarityMatrix = this.st.second();

        initializeComponents();
    }

    /**
     * Inicializa los componentes del panel
     */
    protected void initializeComponents() {
        setLayout(new BorderLayout(10, 10));

        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        SimilarityTableModel tableModel = new SimilarityTableModel(products, similarityMatrix);

        similarityTable = new JTable(tableModel);

        similarityTable.setDefaultRenderer(Object.class, new SimilarityCellRenderer());

        JList<String> rowHeader = new JList<>(products);
        rowHeader.setFixedCellWidth(150);
        rowHeader.setFixedCellHeight(similarityTable.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(similarityTable));

        JScrollPane scrollPane = new JScrollPane(similarityTable);
        scrollPane.setRowHeaderView(rowHeader);
        add(scrollPane, BorderLayout.CENTER);

        saveButton.setPreferredSize(new Dimension(150, 30));
        saveButton.addActionListener(e -> handleSaveRelations());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Maneja el guardado de las relaciones de la tabla de similitud
     */
    private void handleSaveRelations() {
        SimilarityTableModel model = (SimilarityTableModel) similarityTable.getModel();
        float[][] relations = model.getRelations();

        List<Pair<Pair<String, String>, Double>> nuevasSimilitudes = new ArrayList<>();

        for (int i = 0; i < relations.length; i++) {
            for (int j = 0; j < relations[i].length; j++) {
                if (i > j && relations[i][j] != (float) similarityMatrix[i][j]) {
                    nuevasSimilitudes.add(new Pair<>(
                            new Pair<>(products[i], products[j]),
                            (double) relations[i][j]
                    ));
                }
            }
        }

        CtrlPresentation.getInstance().modifySimilarityTable(id, nuevasSimilitudes);

        JOptionPane.showMessageDialog(this, "Tabla de Similitud añadida correctamente.");
        viewPrimary.transitionContentPanel(viewPrimary.getSimilarityTablesManagePanel());
    }


    /**
     * Modelo de la tabla de similitud
     */
    private static class SimilarityTableModel extends AbstractTableModel {
        private String[] products;
        private float[][] relations;

        public SimilarityTableModel(String[] products, double[][] similarityMatrix) {
            this.products = products;
            this.relations = new float[products.length][products.length];

            for (int i = 0; i < similarityMatrix.length; i++) {
                for (int j = 0; j < similarityMatrix[i].length; j++) {
                    this.relations[i][j] = (float) similarityMatrix[i][j];
                }
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


    /**
     * Renderizador de celdas de similitud
     */
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

    /**
     * Renderizador de cabeceras de filas
     */
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
