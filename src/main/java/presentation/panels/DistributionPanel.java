package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;

public class DistributionPanel extends JPanel {
    private ViewPrimary viewPrimary;
    private int id;
    private String[][] distribution;

    public DistributionPanel(ViewPrimary viewPrimary, int id) {
        this.viewPrimary = viewPrimary;
        this.id = id;

        distribution = CtrlPresentation.getInstance().getDistribution(id);

        initializeComponents();
    }

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
        JList<String> orderList = new JList<>(order);

        orderList.setBorder(BorderFactory.createTitledBorder("Orden de los productos:"));
        orderList.setCellRenderer(new AlternatingRowRenderer());
        orderList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        orderList.setVisibleRowCount(1);

        JScrollPane orderScrollPane = new JScrollPane(orderList);
        orderScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        orderScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        orderScrollPane.setPreferredSize(new Dimension(400, 50));
        gbc.gridy++;
        add(orderScrollPane, gbc);
    }

    private static class AlternatingRowRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (!isSelected) {
                component.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            } else {
                component.setBackground(Color.CYAN);
            }

            return component;
        }
    }
}
