package presentation.panels;

import javax.swing.*;

public abstract class AbstractPanel extends JPanel {
    public AbstractPanel() {
        initializeComponents();
    }

    protected abstract void initializeComponents();

}
