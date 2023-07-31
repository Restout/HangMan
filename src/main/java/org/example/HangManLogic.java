package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangManLogic {
    private final int AVAILABLE_MISTAKES = 6;
    private final List<String> wordsLibrary = new ArrayList<>();

    public HangManLogic(File library) {
        try (BufferedReader libraryInputStream = new BufferedReader(new FileReader(library))) {
            String word;
            while ((word = libraryInputStream.readLine()) != null) {
                wordsLibrary.add(word);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {//более подробно расписал бы ексепшены, но проект маленький для такого
            throw new RuntimeException(e);
        }
    }

    private boolean validateLetter(char letter, List<Character> usedLetters) {//мб разделить на два метода(вернее так оно и надо
        if ('a' > letter || letter > 'z') {                                    //но, мне кажется уже чересчур нагромождение будет)
            System.out.println("Symbol should be a letter from English Alphabet");
            return false;
        }
        if (usedLetters.stream().anyMatch(x -> x.equals(letter))) {
            System.out.println("You have already used this letter");
            return false;
        }
        return true;
    }


    private boolean isLetterExistInTheWord(char letter, String word) {
        String letterString = String.valueOf(letter);
        return word.contains(letterString);
    }

    private String selectRandomWord() {
        Random generator = new Random();
        int wordIndex;
        wordIndex = generator.nextInt(0, wordsLibrary.size());
        return wordsLibrary.get(wordIndex);
    }

    private void setLetterInTheUserWord(char letter, String word, char[] userWord) {//Возможно тоже стоит разделить метод
        int index;
        while (word.length() > 0) {
            index = findLetterInTheWord(letter, word);
            if (index == -1) {
                break;
            }
            int indexInRealWord = index + (userWord.length - word.length());
            userWord[indexInRealWord] = letter;
            word = word.substring(index + 1);
        }
    }

    private int findLetterInTheWord(char letter, String word) {
        String charString = String.valueOf(letter);
        charString = charString.toLowerCase();
        return word.indexOf(charString);
    }

    public void setTheGame() {//возможно выделить в отдельный класс, не уверен в целесообразности
        String hiddenWord = selectRandomWord().toLowerCase();
        char[] userWord = new char[hiddenWord.length()];
        List<Character> usedLetters = new ArrayList<>();
        int availableMistakes = AVAILABLE_MISTAKES;
        Scanner in = new Scanner(System.in);
        while (availableMistakes > 0) {
            HangManUI.printHangMan(availableMistakes);
            HangManUI.printUsedLetters(usedLetters);
            HangManUI.printWord(userWord);
            char letter;
            do {
                System.out.println("Select a Letter: ");
                letter = in.next().toLowerCase().charAt(0);

            } while (!validateLetter(letter, usedLetters));
            if (isLetterExistInTheWord(letter, hiddenWord)) {
                setLetterInTheUserWord(letter, hiddenWord, userWord);
            } else {
                availableMistakes--;
                usedLetters.add(letter);
                System.out.println("Mistake!!!");
            }
            if (String.valueOf(userWord).equals(hiddenWord)) {
                System.out.println("You Win!!");
                return;
            }
        }
        HangManUI.printHangMan(availableMistakes);
        System.out.println("You lost:((");
        System.out.println("The word was: " + hiddenWord);

    }
}
