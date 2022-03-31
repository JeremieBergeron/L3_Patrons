package modele;

import observateur.Observable;
import java.io.*;
import java.io.File;
import java.util.LinkedList;

import modele.Perspective;
import vue.VuePerspective;

public class ModelePrincipal extends Observable {

    private /*String*/ File pathImage;

    private Perspective perspectiveFinale1;
    private Perspective perspectiveFinale2;

    public void ouvrir(File pathImage) {
        //this.pathImage = pathImage.toString();
        this.pathImage = pathImage;
        notifierObservers();
    }

    public void sauvegarder (VuePerspective vuePerspectiveFinale1, VuePerspective vuePerspectiveFinale2) {
        System.out.println("modeleprinc");
        LinkedList<Perspective> perspectives=new LinkedList<>();
        //try {
            /*FileOutputStream fileOut =
                    new FileOutputStream( "tmp.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            perspectives.add(vuePerspectiveFinale1.getPerspective());
            perspectives.add(vuePerspectiveFinale2.getPerspective());
            out.writeObject(perspectives);
            out.close();
            fileOut.close();*/
            System.out.println("Serialized data is saved in /tmp/image.ser");
        /*} catch (IOException i) {
            i.printStackTrace();
        }*/
    }

    public /*String*/ File getPathImage() {
        return this.pathImage;
    }
}
