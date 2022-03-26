package vue;

import controleur.ControleurVignette;
import modele.Image;
import observateur.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class VueVignette extends JPanel implements Observer {

    private final Point2D.Float positionVignette = new Point2D.Float(0,0);
    private Image image;

    // panneau
    private float paneauLongueur;
    private float paneauHauteur;

    // L'image
    private float vignetteLongueur;
    private float vignetteHauteur;


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
            positionVignette.x = (paneauLongueur/2) - (vignetteLongueur/2);
            positionVignette.y = (paneauHauteur/2) - (vignetteHauteur/2);

            // Dessiner image
            g.drawImage(image.getImage(), (int) positionVignette.x, (int) positionVignette.y, (int) vignetteLongueur, (int) vignetteHauteur, this);
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
