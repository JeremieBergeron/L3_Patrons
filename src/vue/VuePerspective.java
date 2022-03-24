package vue;

import commande.Commande;
import commande.Dezoomer;
import commande.Zoomer;
import controleur.ControleurPerspective;
import modele.Perspective;
import observateur.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class VuePerspective extends JPanel implements Observer, MouseWheelListener {
    private Perspective perspective;
    private ControleurPerspective ctrlPerspective = new ControleurPerspective(this, perspective);

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

    @Override
    public void update() {

    }

    /**
     * Invoked when the mouse wheel is rotated.
     *
     * @param event : Event
     * @see MouseWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        if (event.getWheelRotation() < 0) {

            Commande cmdZoomer = new Zoomer(perspective);
            ctrlPerspective.executreCommande(cmdZoomer);

        } else {

            Commande cmdDezoomer = new Dezoomer(perspective);
            ctrlPerspective.executreCommande(cmdDezoomer);

        }
    }
}
