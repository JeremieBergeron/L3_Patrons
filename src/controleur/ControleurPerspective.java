package controleur;

import commande.Commande;
import modele.Perspective;
import vue.VuePerspective;

public class ControleurPerspective {
    VuePerspective vuePerspective;
    Perspective perspective;

    /**
     *
     * @param vuePerspective
     * @param perspective
     */
    public ControleurPerspective(VuePerspective vuePerspective, Perspective perspective){
        this.vuePerspective = vuePerspective;
        this.perspective = perspective;

    }

    /**
     *
     * @param commande
     */
    public void executreCommande(Commande commande) {

        //
        commande.execute();
    }
}
