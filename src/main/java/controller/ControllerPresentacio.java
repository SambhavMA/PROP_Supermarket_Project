package controller;

import model.product.EnumType;
import java.util.ArrayList;
import java.util.Vector;
import model.exceptions.ProductNotFoundException;
import model.exceptions.NoTypeWithName;
import model.exceptions.ProductAlreadyExistsException;
import utils.Pair;

//import controller.ControllerDomini;
//import controller.ControllerIO;

public class ControllerPresentacio {
    private ControllerDomini controllerDomini = new ControllerDomini();
    private ControllerIO controllerIO = new ControllerIO(this);

    public ControllerPresentacio() {
        controllerIO.init();
    }

    public void addProduct(String name, String type) throws ProductAlreadyExistsException, NoTypeWithName {
        controllerDomini.addProduct(name, type);
    }

    public void modifyProduct(String name, String type) throws ProductNotFoundException, NoTypeWithName {
        controllerDomini.modifyProduct(name, type);
    }

    public void deleteProduct(String name) throws ProductNotFoundException {
        controllerDomini.deleteProduct(name);
    }

    public Pair<String, String> getProduct(String name) throws ProductNotFoundException {
        return controllerDomini.getProduct(name);
    }

    // public void addSimilarityTable() {}
    // public void modifySimilarityTable() {}
    // public void deleteSimilarityTable() {}
    // public void getSimilarityTable() {}
    //
    //
    // public void generateDistribution() {}
    // public void modifyDistribution() {}
    // public void deleteDistribution(int id) {}
    // public void getDistribution(int id) {}
}
