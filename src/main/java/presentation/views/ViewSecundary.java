package presentation.views;

import javax.swing.*;

public class ViewSecundary {

    private JFrame frame = new JFrame("Secundary View");

    public ViewSecundary() {

    }

    public void start() {
        initializeComponents();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void stop() {
        frame.dispose();
    }

    public void display() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void enable() {
        frame.setEnabled(true);
    }

    public void disable() {
        frame.setEnabled(false);
    }

    private void initializeComponents() {

    }
}
