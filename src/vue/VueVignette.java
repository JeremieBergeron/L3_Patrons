package vue;

import controleur.ControleurVignette;
import modele.Image;
import observateur.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class VueVignette extends JPanel implements Observer {

    private final Point2D.Float POSITION_VIGNETTE = new Point2D.Float(0,0);
    private Image image;

    // panneau
    float paneauLongueur;
    float paneauHauteur;

    // L'image
    float vignetteLongueur;
    float vignetteHauteur;


    /**
     *
     *
     */
    public VueVignette(){
        initPanneau();
        initContenu();

    }

    /**
     *
     */
    public void initPanneau(){
        setLayout(new FlowLayout());
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    /**
     *
     */
    public void initContenu(){

        new ControleurVignette(this, image);

    }

    /**
     *
     * @param image :
     */
    public void setImage (Image image) {
        this.image = image;
    }

    /**
     *
     * @param g :
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);

        if(image != null) {

            // Panneau
            paneauLongueur = this.getWidth();
            paneauHauteur = this.getHeight();

            // L'image
            vignetteLongueur = image.getLongueur();
            vignetteHauteur = image.getHauteur();

            ajusterDimension();

            // Position de la vignette
            POSITION_VIGNETTE.x = (paneauLongueur/2) - (vignetteLongueur/2);
            POSITION_VIGNETTE.y = (paneauHauteur/2) - (vignetteHauteur/2);

            // Dessiner image
            g.drawImage(image.getImage(), (int) POSITION_VIGNETTE.x, (int) POSITION_VIGNETTE.y, (int) vignetteLongueur, (int) vignetteHauteur, this);
        }


    }

    /**
     *
     */
    public void ajusterDimension(){
        // Ajuster la vignette à la vue par rapport à la longueur de l'image
        if(vignetteLongueur > paneauLongueur) {
            vignetteLongueur = paneauLongueur - 20;
            vignetteHauteur = vignetteLongueur * ((float) image.getHauteurRatio() / (float) image.getLongueurRatio());
        }

        // Ajuster la vignette par rapport à l'hauteur de l'image
        // dans le cas où l'hauteur est encore plus grand que celle de la vue
        if (vignetteHauteur > paneauHauteur) {
            vignetteHauteur = paneauHauteur - 20;
            vignetteLongueur = vignetteHauteur * ( (float) image.getLongueurRatio() / (float) image.getHauteurRatio() );
        }
    }



    /**
     *
     */
    @Override
    public void update() {
        repaint();
    }
}
