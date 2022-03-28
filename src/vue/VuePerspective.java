package vue;

import commande.*;
import controleur.ControleurPerspective;
import modele.Perspective;
import observateur.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class VuePerspective extends JPanel implements Observer, MouseWheelListener, MouseMotionListener, MouseListener {

    private boolean vueActive;
    private Point clickOffset;
    private Point positionTemporaire;
    private boolean dragImage;
    private /*final*/ Perspective perspective;
    private /*final*/ ControleurPerspective ctrlPerspective;


    private static final String UNDO = "undo";


    /**
     *
     */
    public VuePerspective() {

        initPanneau();
        initContenu();

    }

    /**
     *
     */
    public void initPanneau() {
        ctrlPerspective = new ControleurPerspective(this, perspective);

        vueActive = false;
        addMouseWheelListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);



        /*setFocusable(true);*/

        setLayout(new FlowLayout());
        setBackground(Color.ORANGE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     *
     */
    public void initContenu() {
        positionTemporaire = new Point();
    }

    /**
     * @param g :
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (perspective != null && perspective.getImage() != null) {

            //BufferedImage i = ImageIO.read(new File(image.getPathImage()));
            BufferedImage i = perspective.getImage().getImage();
            //POSITION_VIGNETTE.y = (this.getHeight()/2) - (i.getHeight()/2)

            g.drawImage(i, positionTemporaire.x, positionTemporaire.y, perspective.getLongueurImage(), perspective.getHauteurImage(), this);
        }
    }

    /**
     * @param perspective :
     */
    public void setPerspective(Perspective perspective) {
        this.perspective = perspective;
    }

    /**
     *
     */
    @Override
    public void update() {
        positionTemporaire.setLocation(perspective.getPosition());

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
        if (vueActive) {

            if (event.getPreciseWheelRotation() < 0) {

                Commande cmdZoomer = new Zoomer(perspective);
                ctrlPerspective.executerCommande(cmdZoomer);

            } else {

                Commande cmdDezoomer = new Dezoomer(perspective);
                ctrlPerspective.executerCommande(cmdDezoomer);

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

        // Inspiré de https://stackoverflow.com/questions/33163298/dragging-image-using-mousedrag-method

        Rectangle bounds = new Rectangle(
                (int) perspective.getPosition().getX(), (int) perspective.getPosition().getY(),
                perspective.getLongueurImage(), perspective.getHauteurImage());
        if (bounds.contains(event.getPoint())) {
            dragImage = true;
            clickOffset = new Point((int) (perspective.getPosition().getX() - event.getPoint().getX()), (int) (perspective.getPosition().getY() - event.getPoint().getY()));
        }


    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param event :
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        if (dragImage) {
            Point dragPoint = new Point(event.getPoint());

            dragPoint.x += clickOffset.x;
            dragPoint.y += clickOffset.y;


            Commande cmdTranslater = new Translater(perspective, dragPoint);
            cmdTranslater.execute();

            dragImage = false;
        }

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param event :
     */
    @Override
    public void mouseEntered(MouseEvent event) {
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW ).put(KeyStroke.getKeyStroke("control Z"), UNDO);
        this.getActionMap().put(UNDO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionnaireCommande.getInstance().removeLastCommande(perspective.getVueType());

            }
        });
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param event :
     */
    @Override
    public void mouseExited(MouseEvent event) {
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Z"), "none");

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
        // Inspiré de https://stackoverflow.com/questions/33163298/dragging-image-using-mousedrag-method
        if (dragImage) {
            Point dragPoint = new Point(event.getPoint());

            dragPoint.x += clickOffset.x;
            dragPoint.y += clickOffset.y;

            positionTemporaire.setLocation(dragPoint);

            repaint();
        }
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
