package vue;

import controleur.ControleurPerspective;
import controleur.ControleurVignette;
import modele.ModelePrincipal;
import modele.Perspective;
import modele.Vignette;

import java.awt.*;


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
        setLayout(new GridBagLayout());


    }

    public void initContenu(){

        Vignette vignette = new Vignette();
        vueVignette = new VueVignette(vignette);
        new ControleurVignette(vueVignette, vignette);

        Perspective perspectiveGauche = new Perspective();
        vuePerspectiveGauche = new VuePerspective(perspectiveGauche);
        new ControleurPerspective(vuePerspectiveGauche, perspectiveGauche);

        Perspective perspectiveDroite = new Perspective();
        vuePerspectiveDroite = new VuePerspective(perspectiveDroite);
        new ControleurPerspective(vuePerspectiveGauche, perspectiveDroite);


        /*// Base sur https://stackoverflow.com/questions/28425321/java-divide-the-screen
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;

        c.weightx = 1/3;
        add(vueVignette, c);
        c.weightx = 1/3;
        add(vuePerspectiveGauche, c);
        c.weightx = 1/3;
        add(vuePerspectiveDroite, c);*/


    }



}
