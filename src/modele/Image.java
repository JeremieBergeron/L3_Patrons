package modele;

import observateur.Observable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Image extends Observable implements java.io.Serializable {

    private transient BufferedImage image;

    private int hauteurImage;
    private int longueurImage;
    private int hauteurRatio;
    private int longueurRatio;
    private Point position = new Point();

    public Image() {

    }

    /**
     * @param image :
     */
    public void setImage(BufferedImage image, int longueurVue, int hauteurVue) {

        this.image = image;

        hauteurImage = this.image.getHeight();
        longueurImage = this.image.getWidth();

        setImgRatio();

        ajusterDimension(longueurVue, hauteurVue);

        position.setLocation(longueurVue / 2 - this.longueurImage / 2, hauteurVue / 2 - this.hauteurImage / 2);

        notifierObservers();
    }

    /**
     * @param longueurVue :
     * @param hauteurVue  :
     */
    private void ajusterDimension(int longueurVue, int hauteurVue) {
        if (longueurImage > longueurVue) {
            longueurImage = longueurVue - 20;
            hauteurImage = (int) (longueurImage * ((float) hauteurRatio / (float) longueurRatio));
        }

        // Ajuster la vignette par rapport à l'hauteur de l'image
        // dans le cas où l'hauteur est encore plus grand que celle de la vue
        if (hauteurImage > hauteurVue) {
            hauteurImage = hauteurVue - 20;
            longueurImage = (int) (hauteurImage * ((float) longueurRatio / (float) hauteurRatio));
        }
    }

    /**
     * @return :
     */
    public BufferedImage getBufferedImage() {
        return this.image;
    }

    /**
     *
     */
    public void setImgRatio() {
        int dim_pgcd = pgcd(hauteurImage, longueurImage);
        hauteurRatio = hauteurImage / dim_pgcd;
        longueurRatio = longueurImage / dim_pgcd;
    }

    /**
     * @param a :
     * @param b :
     * @return :
     */
    public int pgcd(int a, int b) {
        return b == 0 ? a : pgcd(b, a % b);
    }

    /**
     * @return :
     */
    public int getLongueurRatio() {
        return this.longueurRatio;
    }

    /**
     * @return :
     */
    public int getLongueurImage() {
        return this.longueurImage;
    }

    /**
     * @return :
     */
    public int getHauteurRatio() {
        return this.hauteurRatio;
    }

    /**
     * @return :
     */
    public int getHauteurImage() {
        return this.hauteurImage;
    }

    public Point getPosition() {
        return position;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(1); // how many images are serialized?
        ImageIO.write(image, "png", out); // png is lossless
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        in.readInt();
        image = ImageIO.read(in);
    }
}
