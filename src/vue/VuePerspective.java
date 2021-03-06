package vue;

import commande.*;
import controleur.ControleurPerspective;
import modele.Perspective;
import observateur.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class VuePerspective extends JPanel implements Observer, MouseMotionListener, MouseListener {

    // Permet de bouger l'image à partir de n'importe quel point de celle-ci.
    // Explication :
    //  Si cette variable ne serait pas présente, le déplacement de l'image dépendrait entièrement du point en haut à gauche de l'image.
    //  Ainsi, cette variable ajoute un certain x et un certain y pour compenser la distance entre la souris et le point HAUT-GAUCHE de l'image.
    private Point clickOffset;
    // Position de l'image lorsque qu'on clique sur celle-ci et qu'on la bouge sans retirer son doigt de la souris
    private Point positionTemporaire;
    // Si vrai, on peut déplacer l'image
    private boolean dragImage;
    private boolean vueActive = false;

    private Perspective perspective;
    private ControleurPerspective ctrlPerspective;

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

        setFocusable(true);
        //addMouseWheelListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(new FlowLayout());
        setBackground(Color.ORANGE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton button = new JButton();
        button.setFocusable(false);
        button.setText("UNDO");
        button.addActionListener(e ->  {
            if(perspective != null) {

                if (!this.vueActive) {
                    this.ctrlPerspective.notifierAutreVue(this);
                    setVueActive(true);
                }

                ctrlPerspective.deExecuterCommande();
            }
        });
        add(button);
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
            BufferedImage i = perspective.getImage().getBufferedImage();
            g.drawImage(i, positionTemporaire.x, positionTemporaire.y, Math.round(perspective.getLongueurImage()), Math.round(perspective.getHauteurImage()), this);
        }
    }

    /**
     *
     * @param etat :
     */
    public void setVueActive(boolean etat){
        this.vueActive = etat;

        if(this.vueActive) {
            // Ajout du raccourci clavier lorsque la souris est au-dessus de la vue
            this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Z"), UNDO);
            this.getActionMap().put(UNDO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(vueActive && perspective != null) {
                        ctrlPerspective.deExecuterCommande();
                    }
                }
            });
        } else {
            // Retrait du raccourci clavier lorsque la souris est en dehors de la vue
            this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Z"), "none");
        }
    }

    /**
     *
     * @return :
     */
    public boolean getVueEtat(){
        return this.vueActive;
    }

    /**
     *
     * @return :
     */
    public ControleurPerspective getCtrlPerspective() {
        return ctrlPerspective;
    }

    /**
     * Cette méthode doit uniquement être appelé par le contrôleur
     *
     * @param perspective :
     */
    public void setPerspective(Perspective perspective) {
        this.perspective = perspective;
    }

    /**
     *
     * @return :
     */
    public Perspective getPerspective(){
        return perspective;
    }

    /**
     * Vérifie s'il y a une instance de la perspective.
     * @return : true s'il y a une perspective, false dans le cas contraire.
     */
    public boolean verifierPerspective(){
        return this.perspective != null;
    }

    /**
     *
     */
    @Override
    public void update() {
        positionTemporaire.setLocation(perspective.getPosition());

        repaint();
    }

    /* MouseListener */

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param event :
     */
    @Override
    public void mousePressed(MouseEvent event) {

        if(!vueActive) {
            setVueActive(true);
            this.ctrlPerspective.notifierAutreVue(this);
        }

        if (vueActive && perspective != null) {
            // Inspiré de https://stackoverflow.com/questions/33163298/dragging-image-using-mousedrag-method

            Rectangle bounds = new Rectangle(
                    (int) perspective.getPosition().getX(), (int) perspective.getPosition().getY(),
                    Math.round(perspective.getLongueurImage()), Math.round(perspective.getHauteurImage()));
            if (bounds.contains(event.getPoint())) {
                dragImage = true;
                clickOffset = new Point((int) (perspective.getPosition().getX() - event.getPoint().getX()), (int) (perspective.getPosition().getY() - event.getPoint().getY()));
            }
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
            // Envoie la commande pour translater l'image
            Point dragPoint = new Point(event.getPoint());

            dragPoint.x += clickOffset.x;
            dragPoint.y += clickOffset.y;

            Commande cmdTranslater = new Translater(perspective, dragPoint);
            cmdTranslater.execute();

            dragImage = false;
        }
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
            // Permet de faire bouger l'image. IMPORTANT : N'envoie pas de commande
            Point dragPoint = new Point(event.getPoint());

            dragPoint.x += clickOffset.x;
            dragPoint.y += clickOffset.y;

            positionTemporaire.setLocation(dragPoint);

            repaint();
        }
    }


    /* END MouseMotionListener */

    /////////////////////////
    // MÉTHODES PAS UTILISÉES HÉRITÉES DES INTERFACES
    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param event :
     */
    @Override
    public void mouseClicked(MouseEvent event) {}

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param event :
     */
    @Override
    public void mouseMoved(MouseEvent event) {

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
}
