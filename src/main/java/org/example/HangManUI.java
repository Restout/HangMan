package org.example;

import java.util.List;

public class HangManUI {
    private static final String[] frames = {
            "   +---+\n" +
                    "   |   |\n" +
                    "       |\n" +
                    "       |\n" +
                    "       |\n" +
                    "       |\n" +
                    "=========   ",
            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "       |\n" +
                    "       |\n" +
                    "       |\n" +
                    "=========",
            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "   |   |\n" +
                    "       |\n" +
                    "       |\n" +
                    "========= ",
            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "  /|   |\n" +
                    "       |\n" +
                    "       |\n" +
                    "=========",
            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "  /|\\  |\n" +
                    "       |\n" +
                    "       |\n" +
                    "=========",
            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "  /|\\  |\n" +
                    "  /    |\n" +
                    "       |\n" +
                    "=========",
            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "  /|\\  |\n" +
                    "  / \\  |\n" +
                    "       |\n" +
                    "========="};

    public static void printWord(char[] userWord) {
        for (Character letter : userWord) {
            if (letter == '\u0000') {
                System.out.print('_');
            } else {
                System.out.print(letter);
            }
        }
        System.out.println();
    }

    public static void printHangMan(int attemptsLeft) {
        int totalAttempts = frames.length - 1;
        System.out.println(frames[totalAttempts - attemptsLeft]);
    }

    public static void printUsedLetters(List<Character> usedLetters) {
        System.out.print("UsedLetters:");
        for (Character letter : usedLetters) {
            System.out.print("[" + letter + "], ");
        }
        System.out.println();
    }
}
