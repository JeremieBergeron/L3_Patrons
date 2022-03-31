package commande;


import controleur.ControleurPerspective;
import modele.Image;
import modele.ModelePrincipal;
import modele.Perspective;
import vue.VuePerspective;

import java.io.File;
import java.util.LinkedList;

public class Sauvegarder implements Commande {

    private boolean etat = false;
    private ModelePrincipal modelePrincipal;
    private File savedFile;
    //private Image imageSauvegarder;
    //private Perspective perspectiveFinale1;

    private VuePerspective vuePerspectiveFinale1;
    private VuePerspective vuePerspectiveFinale2;

    public Sauvegarder(ModelePrincipal modelePrincipal, VuePerspective vuePerspectiveFinale1, VuePerspective vuePerspectiveFinale2) {
        this.modelePrincipal = modelePrincipal;
        this.vuePerspectiveFinale1 = vuePerspectiveFinale1;
        this.vuePerspectiveFinale2 = vuePerspectiveFinale2;
        System.out.println("classe sauvegarder.java");
    }


    @Override
    public boolean execute() {
        if(etat) {
            modelePrincipal.sauvegarder(vuePerspectiveFinale1, vuePerspectiveFinale2);
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean unexecute() {
        return false;
    }

}
