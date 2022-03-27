package vue;

import commande.Commande;
import commande.Ouvrir;
import commande.Zoomer;
import controleur.ControleurPerspective;
import controleur.ControleurPrincipale;
import controleur.ControleurVignette;
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
        image.setPathImage(path); // Ceci est nécessaire pour faire rafraichir la vue

        // Vue de droite
        Perspective perspectiveDroite = new Perspective(/*image*/);
        perspectiveDroite.ajouterObservers(vuePerspectiveDroite);
        vuePerspectiveDroite.setPerspective(perspectiveDroite);
        perspectiveDroite.setImage(image,vueVignette.getWidth(), vueVignette.getHeight()); // Ceci est nécessaire pour faire rafraichir la vue


        // Vue de gauche
        Perspective perspectiveGauche = new Perspective(/*image*/);
        perspectiveGauche.ajouterObservers(vuePerspectiveGauche);
        vuePerspectiveGauche.setPerspective(perspectiveGauche);
        perspectiveGauche.setImage(image,vueVignette.getWidth(), vueVignette.getHeight()); // Ceci est nécessaire pour faire rafraichir la vue

    }
}
