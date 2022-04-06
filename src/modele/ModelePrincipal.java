package modele;

import observateur.Observable;


import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import java.util.ArrayList;

import vue.VuePerspective;

import javax.imageio.ImageIO;

public class ModelePrincipal extends Observable {

    //private File pathImage;
    private BufferedImage bufferedImage;
    private boolean isSaveLastVersion = false;

    private Perspective perspectiveFinale1;
    private Perspective perspectiveFinale2;
    private ArrayList<Perspective> perspectives = new ArrayList<>();

    public void ouvrir(File pathImage) {
        if (pathImage.getName().contains(".ser")) {
            try {
                //System.out.println("pathImage(ModelePrinc - .ser): " + pathImage);
                FileInputStream fileIn = new FileInputStream(pathImage);
                ObjectInputStream in = new ObjectInputStream(fileIn);

                //System.out.println("Readin " + in.readObject());
                //System.out.println("Readin " + in.readObject());
                System.out.println("Addall : " + this.perspectives.addAll((ArrayList<Perspective>) in.readObject()));

                System.out.println("perspectives : " + this.perspectives.get(0).getImage().getBufferedImage());
                this.bufferedImage = this.perspectives.get(0).getImage().getBufferedImage();

                //int size = in.readInt(); // Read byte count
                //byte[] buffer = new byte[size];
                //in.readFully(buffer); // Make sure you read all bytes of the image
                //BufferedImage image = ImageIO.read(new ByteArrayInputStream(buffer));
                //System.out.println("Image : " + image);
                //perspectives = (ArrayList<Perspective>) in.readObject();

                in.close();

                /*for (int i = 0; i < 2; i++) {
                    perspectives.add(new Perspective(in.read()));
                }*/
            } catch (EOFException e) {
                e.getCause();
                System.out.println("Message 1 : " + e.getMessage());
                e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Message 2 : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            //this.pathImage = pathImage;

            try {
                this.bufferedImage = ImageIO.read(pathImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            out.writeInt(1); // how many images are serialized?
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ImageIO.write(vuePerspectiveFinale1.getPerspective().getImage().getBufferedImage(), "jpg", buffer);

            out.writeInt(buffer.size()); // Prepend image with byte count
            buffer.writeTo(out);         // Write image

            /*for (Perspective perspective:perspectives) {
                out.writeObject(perspective);
            }*/
            out.close();
            fileOut.close();
            this.perspectives.clear();
            isSaveLastVersion = true;
            //System.out.println("Serialized data is saved in " + myFile.getAbsolutePath());
            //System.out.println("Serialized data is saved in " + pathSavedFile + nameSavedFile + ".ser");

        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public boolean getIsSaveLastVersion() {
        return isSaveLastVersion;
    }

    public void setIsSaveLastVersion(boolean isSaveLastVersion) {
        this.isSaveLastVersion = isSaveLastVersion;
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }
}

