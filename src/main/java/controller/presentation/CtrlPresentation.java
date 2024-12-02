package controller.presentation;


import controller.ControllerDomini;
import presentation.views.ViewPrimary;
import presentation.views.ViewSecundary;

import javax.swing.*;

public class CtrlPresentation {
    private ControllerDomini controllerDomini;
    private ViewPrimary viewPrimary;
    private ViewSecundary viewSecundary;

    private CtrlPresentation() {
        viewPrimary = new ViewPrimary();
        viewSecundary = null;
    }

    private static class CtrlPresentationHolder {
        private static final CtrlPresentation INSTANCE = new CtrlPresentation();
    }

    public static CtrlPresentation getInstance() {
        return CtrlPresentationHolder.INSTANCE;
    }

    public void startPresentation() {
        controllerDomini = ControllerDomini.getInstance();
        viewPrimary.start();
        viewPrimary.display();
    }

    public void endPresentation() {
        System.exit(0);
    }

    /**
     * Transiciona de la vista principal a la vista secundaria.
     * Deshabilita la principal que se seguira mostrando, mientras la secundaria se ejecuta en primer plano
     */
    public void transitionPrimary_to_Secundary(JPanel panel) {
        viewPrimary.disable();
        viewSecundary = new ViewSecundary(panel);
        viewSecundary.start();
        viewSecundary.enable();
        viewSecundary.display();
    }

    /**
     * Transiciona de la vista secundaria a la vista primaria.
     * Borra la vista secundaria, y habilita y muestra la vista principal en primer plano
     */
    public void transitionSecundary_to_Primary(){
        viewSecundary.stop();
        viewSecundary = null;
        viewPrimary.enable();
        viewPrimary.display();
    }




}
