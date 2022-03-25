package vue;

import controleur.ControleurVignette;
import modele.Image;
import observateur.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VueVignette extends JPanel implements Observer {

    private Image image;

    private final Point POSITION_VIGNETTE = new Point(0,0);

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
     * @param g :
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);

        try {
            if(image != null) {

                //BufferedImage i = ImageIO.read(new File(image.getPathImage()));
                BufferedImage i = ImageIO.read(image.getPathImage());
                POSITION_VIGNETTE.y = (this.getHeight()/2) - (i.getHeight()/2);

                g.drawImage(i, POSITION_VIGNETTE.x, POSITION_VIGNETTE.y, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     *
     */
    @Override
    public void update() {
        repaint();
    }

    public void setImage (Image image) {
        this.image = image;
    }
}
