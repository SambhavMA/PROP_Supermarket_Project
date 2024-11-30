package presentation.panels;

import javax.swing.*;

abstract class AbstractPanel extends JPanel {
    public AbstractPanel() {
        initializeComponents();
    }

    protected abstract void initializeComponents();

    public void show() {
        this.setVisible(true);
        this.show();
    }

    public void hide() {
        this.setVisible(false);
    }
}
