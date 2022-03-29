package modele;

import observateur.Observable;
import vue.VueType;

import java.awt.*;

public class Perspective extends Observable {

    private Image image;
    private Point position = new Point();
    private int hauteurImage;
    private int longueurImage;
    private VueType vueType;

    /**
     *
     *
     */
    public Perspective(VueType vueType){
        this.vueType = vueType;
        position.x = 1;
        position.y = 1;
    }

    /**
     * Constructeur de copie
     *
     * @param perspectiveACopier
     */
    public Perspective(Perspective perspectiveACopier){
        this.image = perspectiveACopier.getImage();
        this.position.setLocation(perspectiveACopier.getPosition());
        this.hauteurImage = perspectiveACopier.getHauteurImage();
        this.longueurImage = perspectiveACopier.getLongueurImage();
        this.vueType = perspectiveACopier.getVueType();

        this.ajouterObservers(perspectiveACopier.getListeObservers());
    }

    public void translater(Point position){

        System.out.println("Old position");
        System.out.println("x: "+ position.x);
        System.out.println("y: "+ position.y);

        this.position.setLocation(position);

        System.out.println("New position");
        System.out.println("x: "+ position.x);
        System.out.println("y: "+ position.y);
        notifierObservers();
    }

    /**
     *
     */
    public void zoomer(){

        Point centre1 = new Point(longueurImage/2, hauteurImage/2);

        hauteurImage += image.getHauteurRatio();
        longueurImage += image.getLongueurRatio();

        Point centre2 = new Point(longueurImage/2, hauteurImage/2);
        Point centre = new Point(centre1.x - centre2.x, centre1.y - centre2.y);

        translater(new Point(position.x + centre.x, position.y + centre.y));

        notifierObservers();
    }

    /**
     *
     */
    public void dezoomer(){
        Point centre1 = new Point(longueurImage/2, hauteurImage/2);

        hauteurImage -= image.getHauteurRatio();
        longueurImage -= image.getLongueurRatio();

        Point centre2 = new Point(longueurImage/2, hauteurImage/2);
        Point centre = new Point(centre1.x - centre2.x, centre1.y - centre2.y);

        translater(new Point(position.x + centre.x, position.y + centre.y));
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

    public VueType getVueType() {
        return vueType;
    }

    public void setVueType(VueType vueType) {
        this.vueType = vueType;
    }
}
