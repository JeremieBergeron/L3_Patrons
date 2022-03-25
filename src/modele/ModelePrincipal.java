package modele;

import observateur.Observable;

import java.io.File;

public class ModelePrincipal extends Observable {

    private /*String*/ File pathImage;

    public void ouvrir(File pathImage) {
        //this.pathImage = pathImage.toString();
        this.pathImage = pathImage;
        notifierObservers();
    }

    public void sauvegarder () {

    }

    public /*String*/ File getPathImage() {
        return this.pathImage;
    }
}
