package controlleur;

import modele.Perspective;
import modele.Vignette;
import vue.VuePerspective;
import vue.VueVignette;

public class ControlleurPerspective {
    VuePerspective vuePerspective;
    Perspective perspective;

    public ControlleurPerspective(VuePerspective vuePerspective, Perspective perspective){
        this.vuePerspective = vuePerspective;
        this.perspective = perspective;

    }
}
