package modele;

import observateur.Observable;
import vue.VueType;

import java.awt.*;

public class Perspective extends Observable {

    private static final int MAX_ZOOM = 950;
    private static final int MIN_ZOOM =  50;
    private static final int ZOOM = 5;

    private Image image;
    private Point position = new Point();
    private float hauteurImage;
    private float longueurImage;
    private VueType vueType;


    /**
     *
     *
     */
    public Perspective(VueType vueType){
        this.vueType = vueType;
    }

    /**
     * Constructeur de copie
     *
     * @param perspectiveACopier :
     */
    public Perspective(Perspective perspectiveACopier){
        this.image = perspectiveACopier.getImage();
        this.position.setLocation(perspectiveACopier.getPosition());
        this.hauteurImage = perspectiveACopier.getHauteurImage();
        this.longueurImage = perspectiveACopier.getLongueurImage();
        this.vueType = perspectiveACopier.getVueType();

        this.ajouterObservers(perspectiveACopier.getListeObservers());
    }

    /**
     *
     * @param position :
     */
    public void translater(Point position){

        this.position.setLocation(position);

        notifierObservers();
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
        }
    }

    /**
     *
     * @return :
     */
    public Image getImage() {
        return this.image;
    }

    /**
     *
     * @param image :
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
     * @return :
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     *
     * @return :
     */
    public float getLongueurImage() {
        return this.longueurImage;
    }

    /**
     *
     * @return :
     */
    public float getHauteurImage() {
        return this.hauteurImage;
    }

    /**
     *
     * @return :
     */
    public VueType getVueType() {
        return vueType;
    }

    /**
     *
     * @param vueType :
     */
    public void setVueType(VueType vueType) {
        this.vueType = vueType;
    }
}
