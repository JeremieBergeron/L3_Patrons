package commande;

import modele.ModelePrincipale;

public class Ouvrir implements Commande{

    private ModelePrincipale modelePrincipale;

    public void commandeOuvrir(ModelePrincipale modelePrincipale) {
        this.modelePrincipale = modelePrincipale;
    }

    @Override
    public boolean execute(ModelePrincipale modelePrincipale) {
        modelePrincipale.ouvrir();
        return true;
    }

}
