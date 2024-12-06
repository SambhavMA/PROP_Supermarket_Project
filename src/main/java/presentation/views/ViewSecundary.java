package presentation.views;

import controller.presentation.CtrlPresentation;
import presentation.components.DefaultButtons;
import presentation.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewSecundary {

    private JFrame frame = new JFrame("Secundary View");

    private JPanel contentPanel = new JPanel();
    private JPanel infoPanel = new JPanel();;
    private JPanel buttonPanel;

    public ViewSecundary() {
        initializeFrame();
        initializeComponents();
        //transitionInfoPanel();
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

    private void initializeFrame() {
        frame.setMinimumSize(new Dimension(600,300));
        frame.setPreferredSize(frame.getMinimumSize());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CtrlPresentation.getInstance().transitionSecundary_to_Primary();
            }
        });

        frame.getContentPane().add(contentPanel);
    }

    private void initializeComponents() {
        initializeInfoPanel();
        initializeButtons();
    }

    private void initializeInfoPanel() {
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(infoPanel, BorderLayout.CENTER);
    }

    private void initializeButtons() {
        buttonPanel = new DefaultButtons();
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void transitionInfoPanel(JPanel changeToPanel) {
        contentPanel.remove(infoPanel);
        infoPanel = changeToPanel;
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
