package modele;

public class Perspective extends observateur.Observable {

    private Image image;
    private int posX;
    private int posY;
    private double zoom;

    public Perspective(Image image){
        this.image = image;
    }

    public void zoomer(){
        zoom += 10;
    }

    public void dezoomer(){
        zoom -= 10;
    }
}
