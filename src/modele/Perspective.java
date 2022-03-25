package modele;

import observateur.Observable;

import java.awt.*;

public class Perspective extends Observable {

    private Image image;
    private Point position = new Point();
    private int zoomLevel = 2;
    private int hauteur = 20;
    private int longueur = 50;

    /**
     *
     * @param image :
     */
    public Perspective(/*Image image*/){
        //this.image = image;
        position.x = 1;
        position.y = 2;
    }

    /**
     *
     */
    public void zoomer(){
        hauteur++;
        longueur++;

        notifierObservers();
    }

    /**
     *
     */
    public void dezoomer(){
        hauteur--;
        longueur--;

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
     * @return :
     */
    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    /**
     *
     * @return :
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}
