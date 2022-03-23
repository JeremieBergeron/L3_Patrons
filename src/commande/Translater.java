package commande;

import modele.Perspective;

public class Translater {

    private Perspective modelePerspective;

    public void commandeTranslater(Perspective modelePerspective) {
        this.modelePerspective = modelePerspective;
    }

    @Override
    public boolean execute() {
        modelePerspective.translater();
        return true;
    }

    public boolean defaire() {
        modelePerspective.translater();
        return true;
    }

}
