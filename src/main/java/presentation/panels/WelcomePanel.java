package presentation.panels;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private BorderLayout borderLayout = new BorderLayout();
    private JLabel title = new JLabel("Bienvenido a CompraEfectiva");
    private JLabel subtitle = new JLabel("El sistema que genera distribuciones optimas para supermercados");

    public WelcomePanel() {
        initializeComponents();
    }

    private void initializeComponents() {
        initializeLayout();
        initializeText();
    }

    private void initializeLayout() {
        setLayout(borderLayout);
    }

    private void initializeText() {
        add(title, BorderLayout.CENTER);
        add(subtitle, BorderLayout.CENTER);
    }


}
