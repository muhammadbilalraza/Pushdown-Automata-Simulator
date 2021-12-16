package com.PDA;

import java.io.File;



public class Main {

    public static void main(String[] args) {
//        C:\Users\Bilal\IdeaProjects\PDA\src\com\PDA\input.txt
        String fileName = "src/com/PDA/input.txt";
        File file = new File (fileName);
        if (file.exists() && file.canRead()) {
//            FileReader fr = new FileReader (file);
//			fr.printFile();

            PDA pda = new PDA (file);
            pda.performPDA();
        }
        else
            System.out.println("File not found");
    }
}
