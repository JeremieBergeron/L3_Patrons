package commande;

import java.util.LinkedList;

import controleur.ControleurPerspective;
import memento.MementoIF;
import memento.Originator;
import modele.Perspective;

public class GestionnaireCommande {

    private final Originator originator = new Originator();
    private final LinkedList<MementoIF> listeCommandeGauche;
    private final LinkedList<MementoIF> listeCommandeDroite;

    private static final GestionnaireCommande instance = new GestionnaireCommande();

    private GestionnaireCommande(){
        listeCommandeGauche = new LinkedList<>();
        listeCommandeDroite = new LinkedList<>();
    }

    public static GestionnaireCommande getInstance() {
        return instance;
    }


    /**
     *
     * @param perspective :
     */
    public void addCommande(Perspective perspective) {

        switch (perspective.getVueType()) {
            case GAUCHE:
                listeCommandeGauche.add(originator.saveToMemento(perspective));
                break;

            case DROITE:
                listeCommandeDroite.add(originator.saveToMemento(perspective));
                break;
        }

    }

    /**
     *
     * @param controleurPerspective :
     */
    public void removeLastCommande(ControleurPerspective controleurPerspective) {
        switch (controleurPerspective.getPerspective().getVueType()) {
            case GAUCHE:
                restore(controleurPerspective, listeCommandeGauche);
                break;

            case DROITE:
                restore(controleurPerspective, listeCommandeDroite);
                break;
        }
    }

    /**
     *
     * @param controleurPerspective :
     * @param liste :
     */
    private void restore(ControleurPerspective controleurPerspective, LinkedList<MementoIF> liste){
        if (liste.size() != 0) {
            MementoIF mementoIF = liste.getLast();
            Perspective perspective = originator.restoreFromMemento(mementoIF);
            controleurPerspective.setPerspective(perspective);

            liste.removeLast();
        }
    }

    /**
     *
     */
    public void effacerHistoriqueCmd() {
        listeCommandeGauche.clear();
        listeCommandeDroite.clear();
    }

}
