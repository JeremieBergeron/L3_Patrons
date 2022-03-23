package vue;

import controlleur.ControlleurPerspective;
import controlleur.ControlleurVignette;
import modele.ModelePrincipale;
import modele.Perspective;
import modele.Image;


import javax.swing.*;

// import java.awt.Point;


public class PanneauPrincipale extends JPanel {

    private ModelePrincipale modelePrincipale;

    private VueVignette vueVignette;
    private VuePerspective vuePerspectiveGauche;
    private VuePerspective vuePerspectiveDroite;

    /**
     * Create the frame.
     */
    public PanneauPrincipale(ModelePrincipale modelePrincipale) {

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
        new ControlleurVignette(vueVignette, image);

        Perspective perspectiveGauche = new Perspective(image);
        vuePerspectiveGauche = new VuePerspective(perspectiveGauche);
        new ControlleurPerspective(vuePerspectiveGauche, perspectiveGauche);

        Perspective perspectiveDroite = new Perspective(image);
        vuePerspectiveDroite = new VuePerspective(perspectiveDroite);
        new ControlleurPerspective(vuePerspectiveGauche, perspectiveDroite);

        // Ajout des vues
        add(vueVignette);
        add(vuePerspectiveGauche);
        add(vuePerspectiveDroite);


    }



}
