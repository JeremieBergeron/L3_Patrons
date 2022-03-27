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

            // Dessiner image
            g.drawImage(image.getImage(), image.getPosition().x, image.getPosition().y, image.getLongueurImage(), image.getHauteurImage(),this);

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
