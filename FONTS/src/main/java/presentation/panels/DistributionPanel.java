package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Panel que muestra una distribución y permite modificar su orden
 */
public class DistributionPanel extends JPanel {
    private ViewPrimary viewPrimary;
    private int id;
    private String[][] distribution;
    private JList<String> orderList;
    private DefaultListModel<String> listModel;

    private final List<String> lastTwoSelections = new ArrayList<>();

    /**
     * Constructor de la clase
     * @param viewPrimary vista principal
     * @param id identificador de la distribución que se muestra
     */
    public DistributionPanel(ViewPrimary viewPrimary, int id) {
        this.viewPrimary = viewPrimary;
        this.id = id;

        distribution = CtrlPresentation.getInstance().getDistribution(id);

        initializeComponents();
    }

    /**
     * Inicializa los componentes del panel
     */
    protected void initializeComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Distribución ID: " + id, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        String tiempoExe = distribution[3][0];
        JLabel orderLabel = new JLabel("Tiempo de ejecución: " + tiempoExe + " ms", SwingConstants.CENTER);
        orderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy++;
        add(orderLabel, gbc);

        String[] order = distribution[4];

        listModel = new DefaultListModel<>();
        Arrays.stream(order).forEach(listModel::addElement);
        orderList = new JList<>(listModel);

        orderList.setCellRenderer(new AlternatingRowRenderer(lastTwoSelections));
        orderList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        orderList.setVisibleRowCount(1);

        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        orderList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = orderList.getSelectedValue();
                if (selectedValue != null) {
                    updateLastTwoSelections(selectedValue);
                    orderList.repaint();
                }
            }
        });

        JScrollPane orderScrollPane = new JScrollPane(orderList);
        orderScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        orderScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        orderScrollPane.setPreferredSize(new Dimension(400, 50));
        gbc.gridy++;
        add(orderScrollPane, gbc);

        JButton processButton = new JButton("Cambiar orden");
        processButton.addActionListener(e -> {
            if (lastTwoSelections.size() != 2) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, selecciona al menos dos productos consecutivamente.",
                        "Selección inválida",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String product1 = lastTwoSelections.get(0);
            String product2 = lastTwoSelections.get(1);
            processSelectedProducts(product1, product2);

            updateOrderList();
        });
        gbc.gridy++;
        add(processButton, gbc);
    }

    /**
     * Actualiza la cola con los dos ultimos productos seleccionados
     */
    private void updateLastTwoSelections(String selectedValue) {
        if (lastTwoSelections.size() == 2) {
            lastTwoSelections.remove(0);
        }
        lastTwoSelections.add(selectedValue);
    }

    /**
     * Procesa los productos seleccionados para cambiar su posicion
     * @param product1 producto 1
     * @param product2 producto 2
     */
    private void processSelectedProducts(String product1, String product2) {
        try {
            CtrlPresentation.getInstance().swapProducts(this.id, product1, product2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Actualiza la lista de productos
     */
    private void updateOrderList() {
        distribution = CtrlPresentation.getInstance().getDistribution(id);
        String[] updatedOrder = distribution[4];

        listModel.clear();
        Arrays.stream(updatedOrder).forEach(listModel::addElement);
        orderList.clearSelection();

        lastTwoSelections.clear();
    }

    /**
     * Clase que se encarga de renderizar las filas de la lista de forma alterna, para pintarlas de distinto color
     */
    private static class AlternatingRowRenderer extends DefaultListCellRenderer {
        private final List<String> lastTwoSelections;

        public AlternatingRowRenderer(List<String> lastTwoSelections) {
            this.lastTwoSelections = lastTwoSelections;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (lastTwoSelections.contains(value)) {
                component.setBackground(Color.YELLOW);
            } else if (!isSelected) {
                component.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            } else {
                component.setBackground(Color.CYAN);
            }

            return component;
        }
    }
}
