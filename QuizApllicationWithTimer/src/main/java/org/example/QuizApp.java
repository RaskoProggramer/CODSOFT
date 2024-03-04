package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    static int correctCount = 0;
    static int incorrectCount = 0;
    static Timer time = new Timer();
    static boolean timeIsUp = false;
    static Scanner scan = new Scanner(System.in);
    static boolean answered = false;

    public static void main(String[] args) {
        File folder = new File("/home/wethinkcode/QuizApllicationWithTimer/src/main/java/org/example/Quiz");
        File[] files = folder.listFiles();

        if (files != null) {
            System.out.println("Available quizzes:");
            int count = 1;
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(count + ". " + file.getName());
                    count++;
                }
            }

            System.out.println("Enter the number of the quiz you want to take: ");
            int choice = scan.nextInt();
            if (choice < 1 || choice >= count) {
                System.out.println("Invalid choice.");
                return;
            }

            File selectedFile = files[choice - 1];
            System.out.println("You've selected: " + selectedFile.getName());
            startQuiz(selectedFile);
        } else {
            System.out.println("No quiz files found.");
        }
    }

    static void startQuiz(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < 10; i++) {
                startTimer(); // Start the timer for each question
                String question = br.readLine();
                String option = br.readLine();
                String answer = br.readLine();

                System.out.println(question);
                System.out.println(option);
                System.out.println("Your answer (a, b, c, or d): ");
                String userAnswer = null;

                while (userAnswer == null) {
                    if (timeIsUp) {
                        System.out.println("Time's up! The correct answer is: " + answer);
                        incorrectCount++;
                        break; // Exit the while loop if time is up
                    }
                    String input = scan.next().trim().toLowerCase();
                    if (input.matches("[a-d]")) {
                        userAnswer = input;
                    } else {
                        System.out.println("Invalid input. Please enter a, b, c, or d.");
                    }
                }

                // Call method to check answer and update counts
                checkAnswer(userAnswer, answer);

                timeIsUp = false; // Reset timeIsUp flag for the next question
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Correct answers: " + correctCount);
        System.out.println("Incorrect answers: " + incorrectCount);
    }

    static void startTimer() {
        time = new Timer(); // Create a new Timer instance for each question
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                timeIsUp = true;
                System.out.println("Time is Up");
                time.cancel();
                if (!answered) {
                    System.out.println("You didn't answer in time.");
                    scan.nextLine();
                }
            }
        }, 10 * 1000);
    }

    static void checkAnswer(String userAnswer, String answer) {
        if (userAnswer != null) { // Only if the user has entered an answer
            char userFirstLetter = userAnswer.charAt(0);
            char correctFirstLetter = answer.toLowerCase().charAt(0);
            if (userFirstLetter == correctFirstLetter) {
                System.out.println("Correct!");
                correctCount++;
            } else {
                System.out.println("Incorrect. The Correct answer is: " + answer);
                incorrectCount++;
            }
            System.out.println();
        }
    }
}
