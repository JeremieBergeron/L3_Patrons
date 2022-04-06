package vue;

import commande.*;
import controleur.ControleurPrincipale;
import mediateur.VuePerspectiveMediateur;
import modele.ModelePrincipal;
import modele.Perspective;
import modele.Image;
import observateur.Observer;


import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class PanneauPrincipal extends JPanel implements Observer, MouseWheelListener {

    private final ModelePrincipal modelePrincipal;
    private ControleurPrincipale controleurPrincipale;

    private VueVignette vueVignette;
    private VuePerspective vuePerspectiveGauche;
    private VuePerspective vuePerspectiveDroite;

    private boolean imageOuverte = false;

    /**
     * Create the frame.
     */
    public PanneauPrincipal(ModelePrincipal modelePrincipal) {

        this.modelePrincipal = modelePrincipal;

        initPanneau();
        initContenu();

    }

    /**
     *
     */
    public void initPanneau(){
        addMouseWheelListener(this);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    /**
     *
     */
    public void initContenu(){

        modelePrincipal.ajouterObservers(this);
        controleurPrincipale = new ControleurPrincipale(this, modelePrincipal);

        vueVignette = new VueVignette();

        vuePerspectiveGauche = new VuePerspective();

        vuePerspectiveDroite = new VuePerspective();

        VuePerspectiveMediateur.getInstance().addVueInactive(vuePerspectiveGauche);
        VuePerspectiveMediateur.getInstance().addVueInactive(vuePerspectiveDroite);

        // Ajout des vues
        add(vueVignette);
        add(vuePerspectiveGauche);
        add(vuePerspectiveDroite);

    }

    /**
     *
     * @param selectedFile :
     */
    public void ouvrirImage(File selectedFile) {

        Commande cmdOuvrir = new Ouvrir(modelePrincipal ,selectedFile);
        this.controleurPrincipale.executerCommande(cmdOuvrir);
    }

    /**
     *
     * @param nameSavedFile :
     * @param pathSavedFile :
     */
    public void SauvegarderImage(String nameSavedFile, File pathSavedFile) {
        //System.out.print("classe panneauprinc.java");
        Commande cmdSauver = new Sauvegarder(modelePrincipal, vuePerspectiveGauche ,vuePerspectiveDroite, nameSavedFile, pathSavedFile);
        this.controleurPrincipale.executerCommande(cmdSauver);
    }


    /**
     *
     */
    @Override
    public void update() {

        BufferedImage path = this.modelePrincipal.getBufferedImage();

        Image image = new Image();
        image.ajouterObservers(vueVignette);
        vueVignette.setImage(image);
        image.setImage(path,vueVignette.getWidth(), vueVignette.getHeight()); // Ceci est nécessaire pour faire rafraichir la vue

        // Vue de droite
        Perspective perspectiveDroite = new Perspective(VueType.DROITE, modelePrincipal);
        perspectiveDroite.ajouterObservers(vuePerspectiveDroite);
        vuePerspectiveDroite.getCtrlPerspective().setPerspective(perspectiveDroite);
        perspectiveDroite.setImage(image,vuePerspectiveDroite.getWidth(), vuePerspectiveDroite.getHeight()); // Ceci est nécessaire pour faire rafraichir la vue

        // Vue de gauche
        Perspective perspectiveGauche = new Perspective(VueType.GAUCHE, modelePrincipal);
        perspectiveGauche.ajouterObservers(vuePerspectiveGauche);
        vuePerspectiveGauche.getCtrlPerspective().setPerspective(perspectiveGauche);
        perspectiveGauche.setImage(image,vuePerspectiveGauche.getWidth(), vuePerspectiveGauche.getHeight()); // Ceci est nécessaire pour faire rafraichir la vue

        imageOuverte = true;
    }

    /**
     *
     * @return :
     */
    public boolean getImageOuverte(){
        return imageOuverte;
    }

    /**
     *
     * @return :
     */
    public ModelePrincipal getModelePrincipal() {
        return modelePrincipal;
    }

    /**
     * Invoked when the mouse wheel is rotated.
     *  Utilisé pour appeler les commandes zoomer et dezoomer.
     * @param event : Event
     * @see MouseWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        VuePerspective vue = null;

        // Savoir qu'elle est la vue active
        if(vuePerspectiveDroite.getVueEtat()) {
            vue = vuePerspectiveDroite;
        } else if (vuePerspectiveGauche.getVueEtat()) {
            vue = vuePerspectiveGauche;
        }

        //assert vue != null; // ajouté par l'IDE, je ne sais pas vraiment pourquoi, mais cela enlève un avertissement.
        if (vue != null && vue.verifierPerspective()) {
            Commande cmdZoomerDezoomer;

            if (event.getWheelRotation() < 0) {

                cmdZoomerDezoomer = new Zoomer(vue.getPerspective());
                vue.getCtrlPerspective().executerCommande(cmdZoomerDezoomer);

            } else if (event.getWheelRotation() > 0) {

                cmdZoomerDezoomer = new Dezoomer(vue.getPerspective());
                vue.getCtrlPerspective().executerCommande(cmdZoomerDezoomer);

            }
        }
    }
}