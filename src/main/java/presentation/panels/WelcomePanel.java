package presentation.panels;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private JLabel title = new JLabel("Bienvenido a CompraEfectiva", SwingConstants.CENTER);
    private JLabel subtitle = new JLabel("El sistema que genera distribuciones óptimas para supermercados", SwingConstants.CENTER);

    public WelcomePanel() {
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new GridBagLayout());

        title.setFont(new Font("Arial", Font.BOLD, 24));

        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.add(title);
        textPanel.add(subtitle);

        add(textPanel);
    }
}
