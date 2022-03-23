package vue;

import modele.Perspective;

import javax.swing.*;
import java.awt.*;

public class VuePerspective extends JPanel implements observateur.Observer {
    private Perspective perspective;

    public VuePerspective(Perspective perspective){
        this.perspective = perspective;

        initPanneau();
        initContenu();

    }

    public void initPanneau(){
        setLayout(new FlowLayout());
        setBackground(Color.ORANGE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void initContenu(){

    }
}