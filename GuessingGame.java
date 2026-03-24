import java.util.Scanner;
import java.util.Random;

public class GuessingGame {

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    String playerName;
    int secretNumber;
    int attempts;
    final int MAX_ATTEMPTS = 10;

    public void displayWelcome() {
        System.out.println("========================================");
        System.out.println("    WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
        System.out.println("Hello, " + playerName + "!");
        System.out.println();
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have 10 attempts to guess it.");
        System.out.println("I'll give you a hint after each guess.");
        System.out.println();
        System.out.println("Let's begin!");
        System.out.println("========================================");
    }

    public int generateSecretNumber() {
        return rand.nextInt(100) + 1;
    }

    public int getUserGuess() {
        int guess;

        while (true) {
            System.out.print("Enter your guess (1-100): ");
            guess = sc.nextInt();

            if (guess >= 1 && guess <= 100) {
                return guess;
            } else {
                System.out.println("Invalid! Please enter a number between 1 and 100.");
            }
        }
    }

    public void giveHint(int guess) {
        if (guess < secretNumber) {
            System.out.println("Too low! Try a higher number.");
        } else if (guess > secretNumber) {
            System.out.println("Too high! Try a lower number.");
        }
    }

    public void playGame() {
        secretNumber = generateSecretNumber();
        attempts = 0;
        boolean guessed = false;

        while (attempts < MAX_ATTEMPTS) {
            attempts++;
            System.out.println("\n--- Attempt #" + attempts + " ---");

            int guess = getUserGuess();

            if (guess == secretNumber) {
                System.out.println("\nCongratulations " + playerName + "!");
                System.out.println("You guessed the number " + secretNumber + " in " + attempts + " attempts!");
                guessed = true;
                break;
            } else {
                giveHint(guess);
            }
        }

        if (!guessed) {
            System.out.println("\nGAME OVER!");
            System.out.println("You've used all 10 attempts.");
            System.out.println("The secret number was " + secretNumber + ".");
            System.out.println("Better luck next time, " + playerName + "!");
        }
    }

    public void displayStats() {
        String rating;

        if (attempts == 1) rating = "Perfect!";
        else if (attempts <= 3) rating = "Excellent!";
        else if (attempts <= 6) rating = "Good job!";
        else if (attempts <= 10) rating = "Nice try!";
        else rating = "Better luck next time!";

        System.out.println("\n========================================");
        System.out.println("            GAME STATISTICS");
        System.out.println("========================================");
        System.out.println("Player: " + playerName);
        System.out.println("Secret Number: " + secretNumber);
        System.out.println("Attempts Used: " + attempts);
        System.out.println("Rating: " + rating);
        System.out.println("========================================");
    }

    public boolean askPlayAgain() {
        System.out.print("\nWould you like to play again, " + playerName + "? (Y/N): ");
        char choice = sc.next().toUpperCase().charAt(0);
        return choice == 'Y';
    }

    public void startGame() {
        System.out.println("========================================");
        System.out.println("    WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
        System.out.print("Enter your name: ");
        playerName = sc.nextLine();

        do {
            displayWelcome();
            playGame();
            displayStats();
        } while (askPlayAgain());

        System.out.println("\n========================================");
        System.out.println("Thanks for playing, " + playerName + "!");
        System.out.println("See you next time!");
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        game.startGame();
    }
}