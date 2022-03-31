package commande;

import controleur.ControleurPerspective;
import modele.Perspective;

import java.awt.event.MouseWheelEvent;

public class Zoomer implements Commande {

    private Perspective modelePerspective;
    private MouseWheelEvent e;

    public Zoomer(Perspective modelePerspective) {
        this.modelePerspective = modelePerspective;
    }

    @Override
    public boolean execute() {
        GestionnaireCommande.getInstance().addCommande(this, modelePerspective);
        modelePerspective.zoomer();
        return true;
    }

}
