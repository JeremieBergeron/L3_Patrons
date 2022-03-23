package controlleur;

import modele.Vignette;
import vue.VueVignette;

public class ControlleurVignette {

    VueVignette vueVignette;
    Vignette vignette;

    public ControlleurVignette(VueVignette vueVignette, Vignette vignette){
        this.vueVignette = vueVignette;
        this.vignette = vignette;

    }
}
