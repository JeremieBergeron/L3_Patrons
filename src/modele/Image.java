package modele;

import observateur.Observable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends Observable {

    private BufferedImage image;

    private int hauteur;
    private int longueur;
    private int hauteurRatio;
    private int longueurRatio;

    public Image(){

    }

    /**
     *
     * @param pathImage :
     */
    public void setPathImage(File pathImage) {

        try {
            this.image = ImageIO.read(pathImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        hauteur = this.image.getHeight();
        longueur = this.image.getWidth();

        setImgRatio();

        notifierObservers();
    }

    /**
     *
     * @return :
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     *
     */
    public void setImgRatio() {
        int dim_pgcd = pgcd(hauteur, longueur);
        hauteurRatio = hauteur / dim_pgcd;
        longueurRatio = longueur / dim_pgcd;
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
    public int getLongueurRatio() {
        return this.longueurRatio;
    }

    /**
     *
     * @return :
     */
    public int getLongueur(){
        return this.longueur;
    }

    /**
     *
     * @return :
     */
    public int getHauteurRatio () {
        return this.hauteurRatio;
    }

    /**
     *
     * @return :
     */
    public int getHauteur(){
        return this.hauteur;
    }
}
