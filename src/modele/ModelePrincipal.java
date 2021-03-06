package modele;

import observateur.Observable;


import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import java.util.ArrayList;

import vue.PanneauPrincipal;
import vue.VuePerspective;

import javax.imageio.ImageIO;

public class ModelePrincipal extends Observable {

    private PanneauPrincipal panneauPrincipal;

    //private File pathImage;
    private BufferedImage bufferedImage;
    private boolean isSaveLastVersion = false;

    private Perspective perspectiveFinale1;
    private Perspective perspectiveFinale2;
    private ArrayList<Perspective> perspectives = new ArrayList<>();

    public void ouvrir(File pathImage) {
        if (pathImage.getName().contains(".ser")) {
            try {
                FileInputStream fileIn = new FileInputStream(pathImage);
                ObjectInputStream in = new ObjectInputStream(fileIn);

                this.perspectives.addAll((ArrayList<Perspective>) in.readObject());
                in.close();

                // On set le nouveau modelePrincipale
                perspectives.get(0).setModelePrincipal(this);
                perspectives.get(1).setModelePrincipal(this);

                // Ajouter les nouveaux observeurs
                perspectives.get(0).getImage().ajouterObservers(panneauPrincipal.getVueVignette());
                perspectives.get(0).ajouterObservers(panneauPrincipal.getVuePerspectiveGauche());
                perspectives.get(1).ajouterObservers(panneauPrincipal.getVuePerspectiveDroite());


                // Obtenir les vues et y ajouter la perspective désérialiser
                panneauPrincipal.getVueVignette().setImage(perspectives.get(0).getImage());
                panneauPrincipal.getVuePerspectiveGauche().setPerspective(perspectives.get(0));
                panneauPrincipal.getVuePerspectiveDroite().setPerspective(perspectives.get(1));


                // Obtenir les controlleurs et y ajouter la perspective désérialiser
                panneauPrincipal.getVueVignette().getControleurVignette().setImage(perspectives.get(0).getImage());
                panneauPrincipal.getVuePerspectiveGauche().getCtrlPerspective().setPerspective(perspectives.get(0));
                panneauPrincipal.getVuePerspectiveDroite().getCtrlPerspective().setPerspective(perspectives.get(1));

                // Rafraichir les vues
                perspectives.get(0).notifierObservers();
                perspectives.get(1).notifierObservers();
                perspectives.get(0).getImage().notifierObservers();

                perspectives.clear();

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
                notifierObservers();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sauvegarder(VuePerspective vuePerspectiveFinale1, VuePerspective vuePerspectiveFinale2, String nameSavedFile, File pathSavedFile) {
        try {
            File myFile = new File(pathSavedFile + "\\" + nameSavedFile + ".ser");
            FileOutputStream fileOut = new FileOutputStream(myFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            perspectives.add(vuePerspectiveFinale1.getPerspective());
            perspectives.add(vuePerspectiveFinale2.getPerspective());

            out.writeObject(perspectives);

            out.close();
            fileOut.close();
            this.perspectives.clear();
            isSaveLastVersion = true;


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

    public void setPanneauPrincipal(PanneauPrincipal panneauPrincipal) {
        this.panneauPrincipal = panneauPrincipal;
    }
}

