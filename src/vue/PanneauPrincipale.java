package vue;

import controleur.ControleurPerspective;
import controleur.ControleurVignette;
import modele.ModelePrincipal;
import modele.Perspective;
import modele.Image;


import javax.swing.*;

// import java.awt.Point;


public class PanneauPrincipale extends JPanel {

    private ModelePrincipal modelePrincipale;

    private VueVignette vueVignette;
    private VuePerspective vuePerspectiveGauche;
    private VuePerspective vuePerspectiveDroite;

    /**
     * Create the frame.
     */
    public PanneauPrincipale(ModelePrincipal modelePrincipale) {

        this.modelePrincipale = modelePrincipale;

        initPanneau();
        initContenu();

    }

    public void initPanneau(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void initContenu(){

        // Creation des vues
        Image image = new Image();
        vueVignette = new VueVignette(image);
        new ControleurVignette(vueVignette, image);

        Perspective perspectiveGauche = new Perspective(image);
        vuePerspectiveGauche = new VuePerspective(perspectiveGauche);
        new ControleurPerspective(vuePerspectiveGauche, perspectiveGauche);

        Perspective perspectiveDroite = new Perspective(image);
        vuePerspectiveDroite = new VuePerspective(perspectiveDroite);
        new ControleurPerspective(vuePerspectiveGauche, perspectiveDroite);

        // Ajout des vues
        add(vueVignette);
        add(vuePerspectiveGauche);
        add(vuePerspectiveDroite);


    }



}
