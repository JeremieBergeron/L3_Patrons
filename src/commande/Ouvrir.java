package commande;


import modele.ModelePrincipal;

public class Ouvrir implements Commande{

    private ModelePrincipal modelePrincipal;

    public Ouvrir (ModelePrincipal modelePrincipal) {
        this.modelePrincipal = modelePrincipal;
    }

    @Override
    public boolean execute() {
        //modelePrincipal.ouvrir();
        return true;
    }

}
