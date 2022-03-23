package controleur;

import modele.Perspective;
import vue.VuePerspective;

public class ControleurPerspective {
    VuePerspective vuePerspective;
    Perspective perspective;

    public ControleurPerspective(VuePerspective vuePerspective, Perspective perspective){
        this.vuePerspective = vuePerspective;
        this.perspective = perspective;

    }
}
