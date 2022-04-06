package vue;

import controleur.ControleurVignette;
import modele.Image;
import observateur.Observer;

import javax.swing.*;
import java.awt.*;

public class VueVignette extends JPanel implements Observer {

    private Image image;
    private ControleurVignette controleurVignette;

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

        controleurVignette = new ControleurVignette(this, image);

    }

    /**
     *
     * @param image :
     */
    public void setImage (Image image) {
        this.image = image;
    }

    public ControleurVignette getControleurVignette() {
        return controleurVignette;
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
            g.drawImage(image.getBufferedImage(), image.getPosition().x, image.getPosition().y, image.getLongueurImage(), image.getHauteurImage(),this);

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
