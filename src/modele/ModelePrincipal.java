package modele;

import observateur.Observable;

import java.io.File;

public class ModelePrincipal extends Observable {

    private String pathImage;

    public void ouvrir(File pathImage) {
        this.pathImage = pathImage.toString();
        notifierObservers();
    }

    public void sauvegarder () {

    }

    public String getPathImage() {
        return this.pathImage;
    }
}
