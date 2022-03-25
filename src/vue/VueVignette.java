package vue;

import controleur.ControleurVignette;
import modele.Image;

import javax.swing.*;
import java.awt.*;

public class VueVignette extends JPanel implements observateur.Observer {

    private Image image;

    /**
     *
     * @param image :
     */
    public VueVignette(Image image){
        this.image = image;

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
     */
    @Override
    public void update() {

    }

    public void setImage (Image image) {
        this.image = image;
    }
}
