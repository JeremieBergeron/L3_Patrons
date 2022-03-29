package vue;

import commande.Commande;
import commande.Ouvrir;
import controleur.ControleurPrincipale;
import modele.ModelePrincipal;
import modele.Perspective;
import modele.Image;
import observateur.Observer;


import javax.swing.*;
import java.io.File;

// import java.awt.Point;


public class PanneauPrincipal extends JPanel implements Observer {

    private ModelePrincipal modelePrincipal;
    private ControleurPrincipale controleurPrincipale;

    private VueVignette vueVignette;
    private VuePerspective vuePerspectiveGauche;
    private VuePerspective vuePerspectiveDroite;

    /**
     * Create the frame.
     */
    public PanneauPrincipal(ModelePrincipal modelePrincipal) {

        this.modelePrincipal = modelePrincipal;

        initPanneau();
        initContenu();

    }

    public void initPanneau(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void initContenu(){

        modelePrincipal.ajouterObservers(this);
        controleurPrincipale = new ControleurPrincipale(this, modelePrincipal);

        // Creation des vues
        //Image image = new Image();
        vueVignette = new VueVignette();

        //Perspective perspectiveGauche = new Perspective(/*image*/);
        vuePerspectiveGauche = new VuePerspective(/*perspectiveGauche*/);

        //Perspective perspectiveDroite = new Perspective(/*image*/);
        vuePerspectiveDroite = new VuePerspective(/*perspectiveDroite*/);

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
     */
    @Override
    public void update() {

        //String path = this.modelePrincipal.getPathImage();
        File path = this.modelePrincipal.getPathImage();

        Image image = new Image();
        image.ajouterObservers(vueVignette);
        vueVignette.setImage(image);
        //image.setPathImage(path);
        image.setImage(path,vueVignette.getWidth(), vueVignette.getHeight()); // Ceci est nécessaire pour faire rafraichir la vue

        // Vue de droite
        Perspective perspectiveDroite = new Perspective(VueType.DROITE/*image*/);
        perspectiveDroite.ajouterObservers(vuePerspectiveDroite);
        vuePerspectiveDroite.getCtrlPerspective().setPerspective(perspectiveDroite);
        perspectiveDroite.setImage(image,vuePerspectiveDroite.getWidth(), vuePerspectiveDroite.getHeight()); // Ceci est nécessaire pour faire rafraichir la vue

        // Vue de gauche
        Perspective perspectiveGauche = new Perspective(VueType.GAUCHE/*image*/);
        perspectiveGauche.ajouterObservers(vuePerspectiveGauche);
        vuePerspectiveGauche.getCtrlPerspective().setPerspective(perspectiveGauche);
        perspectiveGauche.setImage(image,vuePerspectiveGauche.getWidth(), vuePerspectiveGauche.getHeight()); // Ceci est nécessaire pour faire rafraichir la vue

    }
}
