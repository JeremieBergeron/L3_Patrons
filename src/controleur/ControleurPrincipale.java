package controleur;

import commande.Commande;
import modele.ModelePrincipal;
import vue.PanneauPrincipal;

public class ControleurPrincipale {
    PanneauPrincipal panneauPrincipal;
    ModelePrincipal modelePrincipal;

    public ControleurPrincipale (PanneauPrincipal panneauPrincipal, ModelePrincipal modelePrincipal) {
        this.panneauPrincipal = panneauPrincipal;
        this.modelePrincipal = modelePrincipal;

    }

    /**
     *
     * @param commande :
     */
    public void executerCommande(Commande commande) {

        //
        commande.execute();
    }
}
