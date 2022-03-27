package modele;

import observateur.Observable;

import java.awt.*;

public class Perspective extends Observable {

    private Image image;
    private Point position = new Point();
    private int hauteur;
    private int longueur;

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

        hauteur += image.getHauteurRatio();
        longueur += image.getLongueurRatio();

        notifierObservers();
    }

    /**
     *
     */
    public void dezoomer(){

        hauteur -= image.getHauteurRatio();
        longueur -= image.getLongueurRatio();

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
    public void setImage(Image image) {
        this.image = image;

        hauteur = image.getHauteur();
        longueur = image.getLongueur();

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
    public int getLongueur() {
        return this.longueur;
    }

    /**
     *
     * @return :
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     *
     */
    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    /**
     *
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }


}
