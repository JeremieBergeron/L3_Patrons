package vue;

import modele.Image;

import javax.swing.*;
import java.awt.*;

public class VueVignette extends JPanel implements observateur.Observer {

    private Image image;

    public VueVignette(Image image){
        this.image = image;

        initPanneau();
        initContenu();

    }

    public void initPanneau(){
        setLayout(new FlowLayout());
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    public void initContenu(){

    }

    @Override
    public void update() {

    }
}
