package istic.aco.editor;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        System.out.println("---Bienvenue dans la version 1 de l'editeur de code----");
        StringBuilder bf = new StringBuilder("Bienvenue dans votre editeur");
        System.out.println(bf.length());
        SelectionImpl s = new SelectionImpl(2, 8, bf);
        EngineImpl eg = new EngineImpl(bf, s);
        Scanner sc = new Scanner(System.in);

        int choix;
        do {
            System.out.println("veuillez faire un choix\n1 -> insérer un texte\n2 -> Couper le texte selectionner\n3 -> Copier le texte selectionner\n4 -> Coller un texte\n 0 -> Quitter\n");
            System.out.print("entrez votre choix: ");
            choix = sc.nextInt();
            switch(choix) {
                case 1:
                    System.out.print("entrez votre texte: ");
                    String i = sc.nextLine();
                    if (!i.isEmpty()) {
                        eg.insert(i);
                        System.out.println("");
                        System.out.println("---------------ACO editeur --------------");
                        System.out.println("Selection: " + eg.getSelectionContents());
                        System.out.println("Clipboard: " + eg.getClipboardContents());
                        System.out.println(eg.getBufferContents());
                    }
                    break;
                case 2:
                    eg.cutSelectedText();
                    System.out.println("---------------ACO editeur --------------");
                    System.out.println("Selection: " + eg.getSelectionContents());
                    System.out.println("Clipboard: " + eg.getClipboardContents());
                    System.out.println(eg.getBufferContents());
                    break;
                case 3:
                    eg.copySelectedText();
                    System.out.println("---------------ACO editeur --------------");
                    System.out.println("Selection: " + eg.getSelectionContents());
                    System.out.println("Clipboard: " + eg.getClipboardContents());
                    System.out.println(eg.getBufferContents());
                    break;
                case 4:
                    eg.pasteClipboard();
                    System.out.println("---------------ACO editeur --------------");
                    System.out.println("Selection: " + eg.getSelectionContents());
                    System.out.println("Clipboard: " + eg.getClipboardContents());
                    System.out.println(eg.getBufferContents());
            }
        } while(choix != 0);

        sc.close();
    }
}
