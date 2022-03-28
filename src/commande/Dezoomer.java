package commande;

import modele.Perspective;

public class Dezoomer implements Commande {

    private Perspective modelePerspective;

    public Dezoomer(Perspective modelePerspective) {
        this.modelePerspective = modelePerspective;
    }

    @Override
    public boolean execute() {
        modelePerspective.dezoomer();
        return true;
    }

    public boolean unexecute() {
        modelePerspective.zoomer();
        return true;
    }

}
