package controlleur;

import modele.Perspective;
import vue.VuePerspective;

public class ControlleurPerspective {
    VuePerspective vuePerspective;
    Perspective perspective;

    public ControlleurPerspective(VuePerspective vuePerspective, Perspective perspective){
        this.vuePerspective = vuePerspective;
        this.perspective = perspective;

    }
}
