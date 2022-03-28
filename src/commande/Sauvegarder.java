package commande;


import modele.ModelePrincipal;

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

    @Override
    public boolean unexecute() {
        return false;
    }
}
