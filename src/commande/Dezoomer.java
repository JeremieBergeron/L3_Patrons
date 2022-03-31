package commande;

import controleur.ControleurPerspective;
import modele.Perspective;

public class Dezoomer implements Commande {

    private Perspective modelePerspective;

    public Dezoomer(Perspective modelePerspective) {
        this.modelePerspective = modelePerspective;
    }

    @Override
    public boolean execute() {
        GestionnaireCommande.getInstance().addCommande(this, modelePerspective);
        modelePerspective.dezoomer();
        return true;
    }

}
