package presentation.panels;

import javax.swing.*;

abstract class AppPanel extends JPanel {
    public AppPanel() {
        initializeComponents();
    }

    protected abstract void initializeComponents();

    //public void show() {
    //    this.setVisible(true);
    //

    //public void hide() {
        //this.setVisible(false);
    //}
}
