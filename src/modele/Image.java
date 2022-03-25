package modele;

import observateur.Observable;

import java.io.File;

public class Image extends Observable {

    //private String pathImage;
    private File fileImage;

    public Image(){

    }

    /**
     *
     * @param pathImage :
     */
    public void setPathImage(/*String pathImage*/ File pathImage) {
        //this.pathImage = pathImage;
        this.fileImage = pathImage;
        notifierObservers();

    }

    public /*String*/ File getPathImage() {
        return this.fileImage;
    }
}
