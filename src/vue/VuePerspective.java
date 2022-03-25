package vue;

import commande.Commande;
import commande.Dezoomer;
import commande.Zoomer;
import controleur.ControleurPerspective;
import modele.Perspective;
import observateur.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VuePerspective extends JPanel implements Observer, MouseWheelListener, MouseMotionListener, MouseListener {

    private boolean vueActive;

    private final Perspective perspective;
    private final ControleurPerspective ctrlPerspective;

    /*
    test
    private int id;
    */
    private BufferedImage i;
    private Toolkit t;


    /**
     *
     * @param perspective :
     */
    public VuePerspective(Perspective perspective){
        this.perspective = perspective;
        ctrlPerspective = new ControleurPerspective(this, perspective);
        /*
        test
        this.id = id;
        */

        initPanneau();
        initContenu();

    }

    /**
     *
     */
    public void initPanneau(){
        vueActive = false;
        addMouseWheelListener(this);
        addMouseListener(this);



        setLayout(new FlowLayout());
        setBackground(Color.ORANGE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     *
     */
    public void initContenu() {

        /*test*/
        //t = Toolkit.getDefaultToolkit();
        try {
            i = ImageIO.read(new File("src/img/test1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        perspective.setLongueur(i.getWidth());
        perspective.setHauteur(i.getHeight());

        System.out.println(i.getHeight());
        System.out.println(i.getWidth());
        //System.out.println(i.get);

        perspective.ajouterObservers(this);

    }

    /**
     *
     * @param g :
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(i, perspective.getPosition().x, perspective.getPosition().y, perspective.getLongueur(), perspective.getHauteur(),  this);


    }

    /**
     *
     */
    @Override
    public void update() {
        repaint();
    }

    /**
     * Invoked when the mouse wheel is rotated.
     *
     * @param event : Event
     * @see MouseWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {

        System.out.println(event.isControlDown());

        if(vueActive) {

            if (event.getPreciseWheelRotation() < 0) {

                Commande cmdZoomer = new Zoomer(perspective);
                ctrlPerspective.executreCommande(cmdZoomer);

            } else {

                Commande cmdDezoomer = new Dezoomer(perspective);
                ctrlPerspective.executreCommande(cmdDezoomer);

            }
        }
    }

    /* MouseListener */

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param event :
     */
    @Override
    public void mouseClicked(MouseEvent event) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param event :
     */
    @Override
    public void mousePressed(MouseEvent event) {
        System.out.println("Clicked");

        vueActive = true;
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param event :
     */
    @Override
    public void mouseReleased(MouseEvent event) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param event :
     */
    @Override
    public void mouseEntered(MouseEvent event) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param event :
     */
    @Override
    public void mouseExited(MouseEvent event) {

    }

    /*  END MouseListener  */



    /* MouseMotionListener */

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param event :
     */
    @Override
    public void mouseDragged(MouseEvent event) {

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param event :
     */
    @Override
    public void mouseMoved(MouseEvent event) {

    }


    /* END MouseMotionListener */
}
