package observateur;

import java.util.LinkedList;

public class Observable {

    private LinkedList<Observer> listeObservers = new LinkedList<>();

    public void ajouterObservers(Observer observer) {
        listeObservers.add(observer);
    }

    public void eneleverObserver(Observer observer) {
        listeObservers.remove(observer);
    }

    public void notifierObservers() {
        for (Observer o:listeObservers) {
            o.update();
        }
    }
}
