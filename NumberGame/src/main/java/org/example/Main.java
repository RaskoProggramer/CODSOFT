package org.example;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner guess = new Scanner(System.in);
        int score = 0;
        for (int x = 0; x <= 4; x++){
            int round = x + 1;
            System.out.println("Round "+ round );
            int numberToGuess = random.nextInt(100 + 1);
            System.out.println(numberToGuess);
            boolean correctGuess = false;
            for (int i = 0; i <= 2; i++) {
                System.out.println("Guess a number between 1 - 100");
                int guessed = guess.nextInt();

                if (guessed == numberToGuess){
                    System.out.println("Correct!");
                    correctGuess = true;
                    score ++;
                    break;
                } else if (guessed > numberToGuess) {
                    System.out.println("Guess Lower.");
                }else{
                        System.out.println("Guess High.");
                }
            }
            if (!correctGuess){
                System.out.println("You didn't get the number correct. The number was "+ numberToGuess);
            }
            System.out.println("Your score is "+ score);
        }
        guess.close();
    }
}