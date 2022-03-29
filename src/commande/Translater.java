package commande;

import controleur.ControleurPerspective;
import modele.Perspective;

import java.awt.*;

public class Translater implements Commande {

    private Perspective perspective;
    private Point point = new Point();

    public Translater(Perspective modelePerspective, Point point) {

        this.perspective = modelePerspective;
        this.point.setLocation(point);
    }

    @Override
    public boolean execute() {
        GestionnaireCommande.getInstance().addCommande(this, perspective);

        perspective.translater(point);

        return true;
    }

    public boolean unexecute() {

        return true;
    }

}
