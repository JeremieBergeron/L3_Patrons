package commande;


import controleur.ControleurPerspective;
import modele.Image;
import modele.ModelePrincipal;
import modele.Perspective;
import vue.VuePerspective;

import java.io.File;
import java.util.LinkedList;

public class Sauvegarder implements Commande {

    private boolean etat = true;
    private ModelePrincipal modelePrincipal;

    private VuePerspective vuePerspectiveFinale1;
    private VuePerspective vuePerspectiveFinale2;
    private File pathSavedFile;
    private String nameSavedFile;

    public Sauvegarder(ModelePrincipal modelePrincipal, VuePerspective vuePerspectiveFinale1, VuePerspective vuePerspectiveFinale2, String nameSavedFile, File pathSavedFile) {
        this.modelePrincipal = modelePrincipal;
        this.vuePerspectiveFinale1 = vuePerspectiveFinale1;
        this.vuePerspectiveFinale2 = vuePerspectiveFinale2;
        this.pathSavedFile = pathSavedFile;
        this.nameSavedFile = nameSavedFile;
        System.out.println("classe sauvegarder.java");
    }

    @Override
    public boolean execute() {
        if(etat) {
            modelePrincipal.sauvegarder(vuePerspectiveFinale1, vuePerspectiveFinale2, nameSavedFile, pathSavedFile);
            return true;
        } else{
            return false;
        }
    }

}
