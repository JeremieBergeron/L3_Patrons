package modele;

import observateur.Observable;
import vue.VueType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Perspective extends Observable implements java.io.Serializable {

    private static final int MAX_ZOOM = 950;
    private static final int MIN_ZOOM =  50;
    private static final int ZOOM = 5;

    // MODELE
    private Image image;
    private transient final ModelePrincipal modelePrincipal;

    private final Point position = new Point();
    private float hauteurImage;
    private float longueurImage;
    private final VueType vueType;


    /**
     * Constructeur
     *
     * vueType, modelePrincipal
     */
    public Perspective(VueType vueType, ModelePrincipal modelePrincipal){
        this.vueType = vueType;
        this.modelePrincipal = modelePrincipal;


    }

    /**
     * Constructeur de copie
     *
     * @param : perspectiveACopier
     */
    public Perspective(Perspective perspectiveACopier){
        this.image = perspectiveACopier.getImage();
        this.position.setLocation(perspectiveACopier.getPosition());
        this.hauteurImage = perspectiveACopier.getHauteurImage();
        this.longueurImage = perspectiveACopier.getLongueurImage();
        this.vueType = perspectiveACopier.getVueType();
        this.modelePrincipal = perspectiveACopier.modelePrincipal;

        this.ajouterObservers(perspectiveACopier.getListeObservers());
    }

    /**
     *
     * @param : position
     */
    public void translater(Point position){

        this.position.setLocation(position);

        notifierObservers();
        modelePrincipal.setIsSaveLastVersion(false);
    }

    /**
     *
     */
    public void zoomer(){

        if(longueurImage < MAX_ZOOM && hauteurImage < MAX_ZOOM) {

            Point centre1 = new Point((int)longueurImage/2, (int)hauteurImage/2);

            // Zoom par rapport à la longueur
            float ancienneLongueur = longueurImage;
            longueurImage += ZOOM;
            hauteurImage = (longueurImage * hauteurImage) / ancienneLongueur;
            // Zoom END

            Point centre2 = new Point((int)longueurImage/2, (int)hauteurImage/2);

            // Calculer la difference des centres
            Point centre = new Point(centre1.x - centre2.x, centre1.y - centre2.y);

            // Nouvelle position
            translater( new Point((position.x + centre.x), (position.y + centre.y)));
            notifierObservers();
            modelePrincipal.setIsSaveLastVersion(false);
        }
    }

    /**
     *
     */
    public void dezoomer(){

        if(longueurImage > MIN_ZOOM && hauteurImage > MIN_ZOOM) {

            Point centre1 = new Point((int)longueurImage / 2, (int)hauteurImage / 2);

            // Zoom par rapport à la longueur
            float ancienneLongueur = longueurImage;
            longueurImage -= ZOOM;
            hauteurImage = (longueurImage * hauteurImage) / ancienneLongueur;
            // Zoom END

            Point centre2 = new Point((int)longueurImage / 2, (int)hauteurImage / 2);

            // Calculer la difference des centres
            Point centre = new Point(centre1.x - centre2.x, centre1.y - centre2.y);

            translater( new Point((position.x + centre.x), (position.y + centre.y)));
            notifierObservers();
            modelePrincipal.setIsSaveLastVersion(false);
        }
    }

    /**
     *
     * @return : Image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     *
     * @param : image, longueurVue, hauteurVue
     */
    public void setImage(Image image, int longueurVue, int hauteurVue) {
        this.image = image;

        this.hauteurImage = image.getHauteurImage();
        this.longueurImage = image.getLongueurImage();

        position.setLocation(longueurVue/2 - this.longueurImage/2, hauteurVue/2 - this.hauteurImage/2);

        notifierObservers();
    }


    /**
     *
     * @return : Point
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     *
     * @return : float
     */
    public float getLongueurImage() {
        return this.longueurImage;
    }

    /**
     *
     * @return : float
     */
    public float getHauteurImage() {
        return this.hauteurImage;
    }

    /**
     *
     * @return : VueType
     */
    public VueType getVueType() {
        return vueType;
    }

}
