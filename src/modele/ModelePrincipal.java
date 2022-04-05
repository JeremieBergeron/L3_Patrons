package modele;

import observateur.Observable;


import java.awt.image.RenderedImage;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import vue.VuePerspective;

import javax.imageio.ImageIO;

public class ModelePrincipal extends Observable {

    private File pathImage;
    private boolean isSaveLastVersion = false;

    private Perspective perspectiveFinale1;
    private Perspective perspectiveFinale2;
    private ArrayList<Perspective> perspectives = new ArrayList<>();

    public void ouvrir(File pathImage) {
        if(pathImage.getName().contains(".ser")) {
            try {
                System.out.println("pathImage(ModelePrinc - .ser): "+pathImage);
                FileInputStream fileIn = new FileInputStream(pathImage);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                perspectives = (ArrayList<Perspective>) in.readObject();
                in.close();

                /*for (int i = 0; i < 2; i++) {
                    perspectives.add(new Perspective(in.read()));
                }*/
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            this.pathImage = pathImage;
        }
        notifierObservers();
    }

    public void sauvegarder(VuePerspective vuePerspectiveFinale1, VuePerspective vuePerspectiveFinale2, String nameSavedFile, File pathSavedFile) {
        try {
            File myFile = new File(pathSavedFile + "\\" + nameSavedFile + ".ser");
            FileOutputStream fileOut = new FileOutputStream(myFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            perspectives.add(vuePerspectiveFinale1.getPerspective());
            perspectives.add(vuePerspectiveFinale2.getPerspective());
            //ImageIO.write(vuePerspectiveFinale1.getPerspective().getImage().getImage(), "png", out);
            out.writeObject(perspectives);
            out.close();
            fileOut.close();
            isSaveLastVersion = true;
            //System.out.println("Serialized data is saved in " + myFile.getAbsolutePath());
            //System.out.println("Serialized data is saved in " + pathSavedFile + nameSavedFile + ".ser");

        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public boolean getIsSaveLastVersion(){
        return isSaveLastVersion;
    }

    public void setIsSaveLastVersion(boolean isSaveLastVersion){
        this.isSaveLastVersion = isSaveLastVersion;
    }

    public File getPathImage() {
        return this.pathImage;
    }
}

