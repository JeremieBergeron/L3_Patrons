package memento;

import modele.Perspective;

public class Originator {


    public MementoIF saveToMemento(Perspective perspective) {
        return new Memento(perspective);
    }

    public Perspective restoreFromMemento(MementoIF memento) {
        Memento m = (Memento)memento;
        return m.getSavedState();
    }

    private static class Memento implements MementoIF{
        private final Perspective perspective;

        public Memento(Perspective perspectiveToSave) {
            perspective = new Perspective(perspectiveToSave);
        }

        public Perspective getSavedState() {
            return perspective;
        }
    }
}
