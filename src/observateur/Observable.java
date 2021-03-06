package observateur;

import java.util.LinkedList;
import java.util.List;

public class Observable {

    private transient LinkedList<Observer> listeObservers = new LinkedList<>();

    public List<Observer> getListeObservers(){return listeObservers;}

    public void ajouterObservers(Observer observer) {
        listeObservers.add(observer);
    }

    public void ajouterObservers(List<Observer> observerList) {
        listeObservers.addAll(observerList);
    }

    public void enleverObserver(Observer observer) {
        listeObservers.remove(observer);
    }

    public void notifierObservers() {
        for (Observer o:listeObservers) {
            o.update();
        }
    }
}
