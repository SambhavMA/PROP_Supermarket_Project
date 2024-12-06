package controller.presentation;


import controller.ControllerDomini;
import presentation.views.ViewPrimary;
import presentation.views.ViewSecundary;
import utils.Pair;

public class CtrlPresentation {
    private ControllerDomini controllerDomini;
    private ViewPrimary viewPrimary;
    private ViewSecundary viewSecundary;

    private CtrlPresentation() {
    }

    private static class CtrlPresentationHolder {
        private static final CtrlPresentation INSTANCE = new CtrlPresentation();
    }

    public static CtrlPresentation getInstance() {
        return CtrlPresentationHolder.INSTANCE;
    }

    public void startPresentation() {
        controllerDomini = ControllerDomini.getInstance();
        viewPrimary = new ViewPrimary();
        viewPrimary.display();
    }

    public void endPresentation() {
        System.exit(0);
    }

    /**
     * Transiciona de la vista principal a la vista secundaria.
     * Deshabilita la principal que se seguira mostrando, mientras la secundaria se ejecuta en primer plano
     */
    public void transitionPrimary_to_Secundary() {
        viewPrimary.disable();
        viewSecundary = new ViewSecundary();
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

    public ViewPrimary getViewPrimary() {
        return viewPrimary;
    }

    public Pair<String, String>[] getProducts() {
        try {
            return controllerDomini.getProducts();
        } catch (Exception e) {
            // No products in list
            return null;
        }
    }

    public String[] getProductsCols() {
        return controllerDomini.getProductsCols();
    }


}
