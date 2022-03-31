package controleur;

import commande.Commande;
import commande.GestionnaireCommande;
import modele.Perspective;
import vue.VuePerspective;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelListener;

public class ControleurPerspective implements ActionListener {
    VuePerspective vuePerspective;
    Perspective perspective;

    /**
     *
     * @param vuePerspective :
     * @param perspective :
     */
    public ControleurPerspective(VuePerspective vuePerspective, Perspective perspective){
        this.vuePerspective = vuePerspective;
        this.perspective = perspective;

    }

    /**
     *
     * @param commande :
     */
    public void executerCommande(Commande commande) {

        //
        commande.execute();
    }

    public void deExecuterCommande() {

        //
        GestionnaireCommande.getInstance().removeLastCommande(this);
    }

    public VuePerspective getVuePerspective() {
        return vuePerspective;
    }

    public void setVuePerspective(VuePerspective vuePerspective) {
        this.vuePerspective = vuePerspective;
    }

    public Perspective getPerspective() {
        return perspective;
    }

    public void setPerspective(Perspective perspective) {
        this.perspective = perspective;
        this.vuePerspective.setPerspective(perspective);
        this.perspective.notifierObservers();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e :
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
