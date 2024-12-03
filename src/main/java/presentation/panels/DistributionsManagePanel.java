package presentation.panels;

import javax.swing.*;
import java.awt.*;

public class DistributionsManagePanel extends JPanel {
    private JLabel title = new JLabel("DISTRIBUCIONES", SwingConstants.CENTER);

    public DistributionsManagePanel() {
        super();
        initializeComponents();
    }

    protected void initializeComponents() {
        setLayout(new GridBagLayout());

        title.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.add(title);

        add(textPanel);
    }
}
