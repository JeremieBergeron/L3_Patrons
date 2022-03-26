package modele;

import observateur.Observable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Perspective extends Observable {

    private Image image;
    private Point position = new Point();
    private int hauteurRatio;
    private int longueurRatio;
    private int hauteur;
    private int longueur;

    /**
     *
     *
     */
    public Perspective(/*Image image*/){
        //this.image = image;
        position.x = 1;
        position.y = 1;
    }

    /**
     *
     */
    public void zoomer(){

        hauteur += hauteurRatio;
        longueur += longueurRatio;

        //hauteur++;
        //longueur++;

        notifierObservers();
    }

    /**
     *
     */
    public void dezoomer(){

        hauteur -= hauteurRatio;
        longueur -= longueurRatio;

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

        try {
            BufferedImage i = ImageIO.read(this.image.getPathImage());
            hauteur = i.getHeight();
            longueur = i.getWidth();

        } catch (IOException e) {
            e.printStackTrace();
        }

        int dim_pgcd = pgcd(hauteur, longueur);
        hauteurRatio = hauteur / dim_pgcd;
        longueurRatio = longueur / dim_pgcd;

        notifierObservers();
    }

    /**
     *
     * @param a :
     * @param b :
     * @return :
     */
    public int pgcd(int a, int b) {
        return b == 0 ? a : pgcd(b, a % b);
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
