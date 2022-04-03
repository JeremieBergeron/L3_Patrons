package mediateur;

import vue.VuePerspective;

import java.util.LinkedList;

public class VuePerspectiveMediateur implements Mediateur{

    private final LinkedList<VuePerspective> vuesInactives = new LinkedList<>();
    private VuePerspective vueActive;
    private static final VuePerspectiveMediateur instance = new VuePerspectiveMediateur();

    /**
     *
     * @return :
     */
    public static VuePerspectiveMediateur getInstance() {
        return instance;
    }


    /**
     *
     * @param vue :
     */
    @Override
    public void updateEtatVues(VuePerspective vue) {
        this.vuesInactives.remove(vue);
        if(vueActive != null){
            vueActive.setVueActive(false);
            this.vuesInactives.add(vueActive);
        }
        vueActive = vue;
    }

    /**
     *
     * @param vue :
     */
    public void addVueInactive(VuePerspective vue){
        this.vuesInactives.add(vue);
    }


}
