package controlleur;

import modele.Image;
import vue.VueVignette;

public class ControlleurVignette {

    VueVignette vueVignette;
    Image image;

    public ControlleurVignette(VueVignette vueVignette, Image image){
        this.vueVignette = vueVignette;
        this.image = image;

    }
}
