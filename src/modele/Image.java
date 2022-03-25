package modele;

import observateur.Observable;

public class Image extends Observable {

    // Attention, du a la méthode setPathImage qui est static, cette variable l'est en quelque sorte aussi
    private String pathImage;

    public Image(){

    }

    /**
     *
     * @param pathImage :
     */
    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
        notifierObservers();

    }

    public String getPathImage() {
        return this.pathImage;
    }
}
