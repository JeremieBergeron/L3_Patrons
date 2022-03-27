package commande;

import modele.Perspective;

import java.awt.*;

public class Translater implements Commande {

    private Perspective modelePerspective;
    private Point point;

    public Translater(Perspective modelePerspective, Point point) {

        this.modelePerspective = modelePerspective;
        this.point = point;
    }

    @Override
    public boolean execute() {
        modelePerspective.translater(point);
        return true;
    }

    public boolean defaire() {
        modelePerspective.translater(point);
        return true;
    }

}
