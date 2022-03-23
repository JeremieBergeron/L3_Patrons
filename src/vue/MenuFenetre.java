package vue;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import java.awt.event.ActionEvent;
import java.io.File;

public class MenuFenetre extends JMenuBar {

    private static final String NOM_BTN_MENU = "Menu";
    private static final String NOM_BTN_OUVRIR = "Ouvrir";
    private static final String NOM_BTN_SAUVGARDER = "Sauvgarder";

    /**
     *
     */
    public MenuFenetre() {
        initContenu();
    }

    /**
     *
     */
    public void initContenu() {
        JMenu btnMenu= new JMenu(NOM_BTN_MENU);
        JMenuItem btnOuvrir = new JMenuItem(NOM_BTN_OUVRIR);
        JMenuItem btnSauvgarder = new JMenuItem(NOM_BTN_SAUVGARDER);

        btnOuvrir.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Sélectionnez un fichier");
            fileChooser.setAcceptAllFileFilterUsed(true);

            // Créer un filtre
            /*FileNameExtensionFilter filtre = new FileNameExtensionFilter(".jpg", "jpg");
            fileChooser.addChoosableFileFilter(filtre);*/

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
            }
        });

        btnMenu.add(btnOuvrir);
        btnMenu.add(btnSauvgarder);
        add(btnMenu);
    }
}
