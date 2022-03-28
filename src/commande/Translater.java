package commande;

import modele.Perspective;

import java.awt.*;

public class Translater implements Commande {

    private Perspective modelePerspective;
    private Point newPoint = new Point();
    private Point oldPoint= new Point();

    public Translater(Perspective modelePerspective, Point point) {

        this.modelePerspective = modelePerspective;
        this.newPoint.setLocation(point);
        this.oldPoint.setLocation(modelePerspective.getPosition());
    }

    @Override
    public boolean execute() {
        modelePerspective.translater(newPoint);

        GestionnaireCommande.getInstance().addCommande(this, modelePerspective.getVueType());
        return true;
    }

    public boolean unexecute() {


        modelePerspective.translater(oldPoint);
        return true;
    }

}
