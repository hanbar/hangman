import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String[] words = {"java", "Skillmea", "programming"};
        final Random random = new Random();
        final String wordToGuess = selectWord(words, random);
        String hiddenWord = generateHiddenWord(wordToGuess);
        System.out.println("Welcome to Hangman!");
        System.out.println("Guess the word: " + hiddenWord);

        final int MAX_INCORRECT_GUESSES = 3;
        int incorrectGuesses = 0;

        while (incorrectGuesses < MAX_INCORRECT_GUESSES && hiddenWord.contains("_")) {
            System.out.print("Enter a letter: ");
            char guess = scanLetter(scanner);

            if (wordToGuess.contains(String.valueOf(guess))) {
                hiddenWord = revealLetters(wordToGuess, hiddenWord, guess);
                System.out.println("Correct guess! Updated word: " + hiddenWord);
            } else {
                incorrectGuesses++;
                System.out.println("Incorect guess! You have " + (MAX_INCORRECT_GUESSES - incorrectGuesses) + " guesses left.");
            }
        }

        if (!hiddenWord.contains("_")) {
            System.out.println("Congrats!");
        } else {
            System.out.println("Game over! The word was " + wordToGuess);
        }
    }

    public static char scanLetter(Scanner scanner) {
        char guess;

        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.length() != 1) {
                    throw new Exception("Line length is not 1. Enter a single letter");
                }

                guess = line.charAt(0);
                if (!Character.isLetter(guess)) {
                    throw new Exception("Char is not a letter.");
                }

                break;
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }

        return guess;
    }

    public static String selectWord(String[] words, Random random) {
        return words[random.nextInt(words.length)];
    }

    public static String generateHiddenWord(String word) {
        return "_".repeat(word.length());
    }

    public static String revealLetters(String wordToGuess, String hiddenWord, char letter) {
       char[] hiddenWordChars = hiddenWord.toCharArray();
       for (int i = 0; i < wordToGuess.length(); i++) {
           if (wordToGuess.charAt(i) == letter) {
               hiddenWordChars[i] = letter;
           }
       }

       return String.valueOf(hiddenWordChars);
    }
}