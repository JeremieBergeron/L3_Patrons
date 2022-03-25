package modele;

public class Image {

    // Attention, du a la méthode setPathImage qui est static, cette variable l'est en quelque sorte aussi
    private String pathImage;

    public Image(String path){
        this.pathImage = path;

    }

    public static void setPathImage(String pathImage) {
        pathImage = pathImage;
    }
}
