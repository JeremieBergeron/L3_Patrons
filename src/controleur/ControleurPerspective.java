package controleur;

import commande.Commande;
import commande.GestionnaireCommande;
import mediateur.VuePerspectiveMediateur;
import modele.Perspective;
import vue.VuePerspective;

public class ControleurPerspective  {
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

    /**
     *
     */
    public void deExecuterCommande() {

        //
        GestionnaireCommande.getInstance().removeLastCommande(this);
    }

    /**
     *
     * @return :
     */
    public VuePerspective getVuePerspective() {
        return vuePerspective;
    }

    public void setVuePerspective(VuePerspective vuePerspective) {
        this.vuePerspective = vuePerspective;
    }

    /**
     *
     * @return :
     */
    public Perspective getPerspective() {
        return perspective;
    }

    /**
     *
     * @param perspective :
     */
    public void setPerspective(Perspective perspective) {
        this.perspective = perspective;
        this.vuePerspective.setPerspective(perspective);
        this.perspective.notifierObservers();
    }

    /**
     *
     * @param vue :
     */
    public void notifierAutreVue(VuePerspective vue){
        VuePerspectiveMediateur.getInstance().updateEtatVues(vue);
    }

}
