package commande;

import controleur.ControleurPerspective;
import modele.Perspective;

public interface Commande {

    // Si vrai, peut etre executer. Si faux, ne peut être executer
    boolean execute();
}
