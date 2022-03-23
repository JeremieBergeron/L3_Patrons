package commande;

import modele.ModelePrincipale;

public class Sauvgarder implements Commande {

    private boolean etat = false;
    private ModelePrincipale modelePrincipale;

    public void commandeSauvegarder(ModelePrincipale modelePrincipale) {
        this.modelePrincipale = modelePrincipale;
    }

    @Override
    public boolean execute() {
        if(etat) {
            modelePrincipale.sauvegarder();
            return true;
        } else{
            return false;
        }
    }
}
