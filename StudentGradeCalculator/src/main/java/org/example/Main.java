package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] subjects = {"Math", "English", "Science", "Accounting", "Biology", "Economics"};
        Scanner mark = new Scanner(System.in);
        int[] marks = new int[subjects.length];
        int sum = 0;
        for (int i = 0; i < subjects.length; i++){
            System.out.println(subjects[i] + " : mark out of 100");
            marks[i] = mark.nextInt();
            sum += marks[i];
        }
        System.out.println("Student marks");
        for (int i = 0; i < subjects.length; i++){
            System.out.println(subjects[i] + " : " + marks[i] + "%");
        }
        int average = sum / subjects.length;
        System.out.println("Average : " + average + "%");
        char grade;
        if (average >= 80){
            grade = 'A';
        } else if (average >= 70) {
            grade = 'B';
        } else if (average >= 60) {
            grade = 'C';
        } else if (average >= 50) {
            grade = 'D';
        }else {
            grade = 'F';
        }
        System.out.println("Grade : "+ grade);
        mark.close();
    }
}