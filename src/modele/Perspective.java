package modele;

import observateur.Observable;

import java.awt.*;

public class Perspective extends Observable {

    private Image image;
    private Point position = new Point();
    private int hauteurImage;
    private int longueurImage;

    /**
     *
     *
     */
    public Perspective(){
        position.x = 1;
        position.y = 1;
    }

    public void translater(Point position){

        this.position.setLocation(position);

        notifierObservers();
    }

    /**
     *
     */
    public void zoomer(){

        hauteurImage += image.getHauteurRatio();
        longueurImage += image.getLongueurRatio();

        notifierObservers();
    }

    /**
     *
     */
    public void dezoomer(){

        hauteurImage -= image.getHauteurRatio();
        longueurImage -= image.getLongueurRatio();

        notifierObservers();
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

        this.hauteurImage = image.getHauteur();
        this.longueurImage = image.getLongueur();

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
    public int getLongueurImage() {
        return this.longueurImage;
    }

    /**
     *
     * @return :
     */
    public int getHauteurImage() {
        return this.hauteurImage;
    }

    /**
     *
     */
    public void setLongueurImage(int longueurImage) {
        this.longueurImage = longueurImage;
    }

    /**
     *
     */
    public void setHauteurImage(int hauteurImage) {
        this.hauteurImage = hauteurImage;
    }


}
