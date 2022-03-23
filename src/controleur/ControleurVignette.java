package controleur;

import modele.Image;
import vue.VueVignette;

public class ControleurVignette {

    VueVignette vueVignette;
    Image image;

    public ControleurVignette(VueVignette vueVignette, Image image){
        this.vueVignette = vueVignette;
        this.image = image;

    }
}
