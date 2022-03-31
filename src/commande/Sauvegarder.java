package commande;


import controleur.ControleurPerspective;
import modele.ModelePrincipal;
import modele.Perspective;

public class Sauvegarder implements Commande {

    private boolean etat = false;
    private ModelePrincipal modelePrincipal;

    public Sauvegarder(ModelePrincipal modelePrincipal) {
        this.modelePrincipal = modelePrincipal;
    }

    @Override
    public boolean execute() {
        if(etat) {
            //modelePrincipal.sauvegarder();
            return true;
        } else{
            return false;
        }
    }

}
