package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        File file = new File("src/main/resources/words.txt");
        HangManLogic hangMan = new HangManLogic(file);
        char gameStarter;
        System.out.println("To start The Game Press Y");
        gameStarter = in.next().toLowerCase().charAt(0);
        while (gameStarter == 'y') {
            hangMan.setTheGame();
            System.out.println("Want to continue? Y-yes Any Other Letter - exit");
            gameStarter = in.next().toLowerCase().charAt(0);
        }
    }
}