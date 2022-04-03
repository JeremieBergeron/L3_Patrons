package commande;

import modele.Perspective;

public class Dezoomer implements Commande {

    private final Perspective modelePerspective;

    public Dezoomer(Perspective modelePerspective) {
        this.modelePerspective = modelePerspective;
    }

    @Override
    public boolean execute() {
        GestionnaireCommande.getInstance().addCommande(modelePerspective);
        modelePerspective.dezoomer();
        return true;
    }

}
