package commande;

import modele.Perspective;

public class Zoomer implements Commande {

    private Perspective modelePerspective;

    public void commandeZoomer(Perspective modelePerspective) {
        this.modelePerspective = modelePerspective;
    }

    @Override
    public boolean execute() {
        modelePerspective.zoomer();
        return true;
    }

    public boolean defaire() {
        modelePerspective.dezoomer();
        return true;
    }

}
