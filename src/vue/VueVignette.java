package vue;

import modele.Vignette;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class VueVignette extends JPanel {

    private Vignette vignette;

    public VueVignette(Vignette vignette){
        this.vignette = vignette;

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
}
