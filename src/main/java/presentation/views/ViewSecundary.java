package presentation.views;

import presentation.panels.*;

import javax.swing.*;
import java.awt.*;

public class ViewSecundary {

    private JFrame frame = new JFrame("Secundary View");
    private JPanel contentPanel;

    public ViewSecundary(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public void start() {
        initializeFrame();
    }

    public void stop() {

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

    private void initializeFrame() {
        frame.setMinimumSize(new Dimension(600,300));
        frame.setPreferredSize(frame.getMinimumSize());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
    }
}
