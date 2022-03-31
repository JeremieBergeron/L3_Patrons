package modele;

import observateur.Observable;
import java.io.*;
import java.io.File;
import java.util.LinkedList;

import modele.Perspective;
import vue.VuePerspective;

public class ModelePrincipal extends Observable {

    private /*String*/ File pathImage;

    private Perspective perspectiveFinale1;
    private Perspective perspectiveFinale2;

    public void ouvrir(File pathImage) {
        //this.pathImage = pathImage.toString();
        this.pathImage = pathImage;
        notifierObservers();
    }

    public void sauvegarder (VuePerspective vuePerspectiveFinale1, VuePerspective vuePerspectiveFinale2, String nameSavedFile, File pathSavedFile) {
        System.out.println("modeleprinc");
        LinkedList<Perspective> perspectives = new LinkedList<>();
        try {
            FileOutputStream fileOut = new FileOutputStream( pathSavedFile + nameSavedFile + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            perspectives.add(vuePerspectiveFinale1.getPerspective());
            perspectives.add(vuePerspectiveFinale2.getPerspective());
            out.writeObject(perspectives);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + pathSavedFile + nameSavedFile + ".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /*private void writeObject( ObjectOutputStream out ) throws IOException {
        out.write(this);
        out.flush();
    }*/
    //private Object readObject( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        //Simple s = (Simple) in.read();
        //s.setWin( new JFrame( s.getUsername() ) );
        //...any other extra setup can be done here
        //return s;

        /*try {
        FileInputStream fis=new FileInputStream("C://object.ser");
        ObjectInputStream ois=new ObjectInputStream(fis);
        WriteObject wo=null;
        WriteObject[] woj=new WriteObject[5];

        ArrayList<WriteObject> woi=new ArrayList<>();
        woi=(ArrayList<WriteObject>)ois.readObject();

        for(int i=0;i<woi.size();i++){
            woi.get(i).getvalues();
        } catch (IOException i) {
            i.printStackTrace();
        }*/
    //}

    public /*String*/ File getPathImage() {
        return this.pathImage;
    }
}
