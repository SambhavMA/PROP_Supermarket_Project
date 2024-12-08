package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;

public class DistributionPanel extends JPanel {
    private ViewPrimary viewPrimary;
    private int id;

    public DistributionPanel(ViewPrimary viewPrimary, int id) {
        this.viewPrimary = viewPrimary;
        this.id = id;

        initializeComponents();
    }

    protected void initializeComponents() {

    }
}
