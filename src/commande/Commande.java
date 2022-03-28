package commande;

public interface Commande {

    // Si vrai, peut etre executer. Si faux, ne peut être executer
    boolean execute();
    // Si vrai, peut etre executer. Si faux, ne peut être executer
    boolean unexecute();
}
