package modele;

import observateur.Observable;


import java.awt.image.RenderedImage;
import java.io.*;
import java.io.File;
import java.util.LinkedList;

import vue.VuePerspective;

import javax.imageio.ImageIO;

public class ModelePrincipal extends Observable {

    private File pathImage;
    private boolean isSaveLastVersion = false;

    private Perspective perspectiveFinale1;
    private Perspective perspectiveFinale2;
    private LinkedList<Perspective> perspectives = new LinkedList<>();

    public void ouvrir(File pathImage) {
        if(pathImage.getName().contains(".ser")) {
            try {
                System.out.println(pathImage);
                FileInputStream fileIn = new FileInputStream(pathImage);
                ObjectInputStream in = new ObjectInputStream(fileIn);

                perspectives = (LinkedList<Perspective>) in.readObject();

                for (int i = 0; i < 1; i++) {
                    //perspectives.add(new Perspective());
                }
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
        //System.out.println("modeleprinc.java");
        try {
            File myFile = new File(pathSavedFile + "\\" + nameSavedFile + ".ser");
            FileOutputStream fileOut = new FileOutputStream(myFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            perspectives.add(vuePerspectiveFinale1.getPerspective());
            perspectives.add(vuePerspectiveFinale2.getPerspective());
            out.writeObject(perspectives);
            out.close();
            fileOut.close();
            isSaveLastVersion = true;
            System.out.println("Serialized data is saved in " + myFile.getAbsolutePath());
            //System.out.println("Serialized data is saved in " + myFile.getAbsolutePath());
            //System.out.println("Serialized data is saved in " + pathSavedFile + nameSavedFile + ".ser");

            //transient List<BufferedImage> images;
            /*out.defaultWriteObject();
            out.writeInt(images.size()); // how many images are serialized?
            for (BufferedImage eachImage : images) {
                ImageIO.write(eachImage, "png", out); // png is lossless
            }*/
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

    //private Object readObject( ObjectInputStream in ) throws IOException, ClassNotFoundException {
    //Simple s = (Simple) in.read();
    //s.setWin( new JFrame( s.getUsername() ) );
    //...any other extra setup can be done here
    //return s;


    public File getPathImage() {
        return this.pathImage;
    }
}
