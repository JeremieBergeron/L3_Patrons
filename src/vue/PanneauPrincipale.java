package vue;

import controlleur.ControlleurPerspective;
import controlleur.ControlleurVignette;
import modele.Perspective;
import modele.Vignette;

import java.awt.*;


import javax.swing.*;

// import java.awt.Point;


public class PanneauPrincipale extends JPanel {

    private MonModele monModele;

    private VueVignette vueVignette;
    private VuePerspective vuePerspectiveGauche;
    private VuePerspective vuePerspectiveDroite;

    /**
     * Create the frame.
     */
    public PanneauPrincipale(MonModele monModele) {

        this.monModele = monModele;

        initPanneau();
        initContenu();

    }

    public void initPanneau(){
        setLayout(new GridBagLayout());


    }

    public void initContenu(){

        Vignette vignette = new Vignette();
        vueVignette = new VueVignette(vignette);
        new ControlleurVignette(vueVignette, vignette);

        Perspective perspectiveGauche = new Perspective();
        vuePerspectiveGauche = new VuePerspective(perspectiveGauche);
        new ControlleurPerspective(vuePerspectiveGauche, perspectiveGauche);

        Perspective perspectiveDroite = new Perspective();
        vuePerspectiveDroite = new VuePerspective(perspectiveDroite);
        new ControlleurPerspective(vuePerspectiveGauche, perspectiveDroite);


        // Base sur https://stackoverflow.com/questions/28425321/java-divide-the-screen
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;

        c.weightx = 1/3;
        add(vueVignette, c);
        c.weightx = 1/3;
        add(vuePerspectiveGauche, c);
        c.weightx = 1/3;
        add(vuePerspectiveDroite, c);


    }



}
