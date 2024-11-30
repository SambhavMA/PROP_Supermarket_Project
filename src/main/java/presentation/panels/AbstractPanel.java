package presentation.panels;

import javax.swing.*;

abstract class AbstractPanel extends JPanel {
    public AbstractPanel() {
        initializeComponents();
    }

    protected abstract void initializeComponents();

}
