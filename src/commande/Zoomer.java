package commande;

import modele.Perspective;

public class Zoomer implements Commande {

    private Perspective modelePerspective;

    public Zoomer(Perspective modelePerspective) {
        this.modelePerspective = modelePerspective;
    }

    @Override
    public boolean execute() {
        modelePerspective.zoomer();
        return true;
    }

    public boolean unexecute() {
        modelePerspective.dezoomer();
        return true;
    }

}
