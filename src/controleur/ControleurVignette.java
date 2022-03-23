package controleur;

import modele.Vignette;
import vue.VueVignette;

public class ControleurVignette {

    VueVignette vueVignette;
    Vignette vignette;

    public ControleurVignette(VueVignette vueVignette, Vignette vignette){
        this.vueVignette = vueVignette;
        this.vignette = vignette;

    }
}
