package commande;

import modele.Perspective;

public class Translater implements Commande {

    private Perspective modelePerspective;

    public void Translater(Perspective modelePerspective) {
        this.modelePerspective = modelePerspective;
    }

    @Override
    public boolean execute() {
        //modelePerspective.translater();
        return true;
    }

    public boolean defaire() {
        //modelePerspective.translater();
        return true;
    }

}
