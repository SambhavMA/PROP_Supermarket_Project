package presentation.panels;

import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;

/**
 * Panel del menú superior de la aplicación que sirve para cambiar entre secciones de la aplicación
 */
public class MenuPanel extends JPanel {
    private ViewPrimary viewPrimary;

    private JButton manageProductsButton = new JButton("Gestionar Productos");
    private JButton manageTablesButton = new JButton("<html><div style='text-align: center;'>Gestionar<br>Tablas de Similitud</div></html>");
    private JButton manageDistributionsButton = new JButton("<html><div style='text-align: center;'>Gestionar<br>Distribuciones</div></html>");

    /**
     * Constructor de la clase
     * @param viewPrimary vista principal
     */
    public MenuPanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        initializeComponents();
    }

    /**
     * Inicializa los componentes del panel
     */
    private void initializeComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // size
        manageProductsButton.setPreferredSize(new Dimension(200, 45));
        manageTablesButton.setPreferredSize(new Dimension(200, 45));
        manageDistributionsButton.setPreferredSize(new Dimension(200, 45));

        // cursor: pointer
        manageProductsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        manageTablesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        manageDistributionsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // font
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        manageProductsButton.setFont(buttonFont);
        manageTablesButton.setFont(buttonFont);
        manageDistributionsButton.setFont(buttonFont);

        // actions
        manageProductsButton.addActionListener(e -> viewPrimary.transitionContentPanel(viewPrimary.getProductsManagePanel()));
        manageTablesButton.addActionListener(e -> viewPrimary.transitionContentPanel(viewPrimary.getSimilarityTablesManagePanel()));
        manageDistributionsButton.addActionListener(e -> viewPrimary.transitionContentPanel(viewPrimary.getDistributionsManagePanel()));

        add(manageProductsButton);
        add(manageTablesButton);
        add(manageDistributionsButton);

        // para que quitar el focus feo al primer button
        SwingUtilities.invokeLater(this::requestFocusInWindow);

    }

    /**
     * Actualiza los colores de los botones del menú para indicar en qué apartado se encuentra el usuario
     * @param selectedPanel panel seleccionado
     */
    public void updateButtonColors(JPanel selectedPanel) {
        manageProductsButton.setBackground(null);
        manageTablesButton.setBackground(null);
        manageDistributionsButton.setBackground(null);

        if (selectedPanel instanceof ProductsManagePanel) {
            manageProductsButton.setBackground(new Color(229, 172, 162));
        } else if (selectedPanel instanceof SimilarityTablesManagePanel) {
            manageTablesButton.setBackground(new Color(229, 172, 162));
        } else if (selectedPanel instanceof DistributionsManagePanel) {
            manageDistributionsButton.setBackground(new Color(229, 172, 162));
        }
    }
}
