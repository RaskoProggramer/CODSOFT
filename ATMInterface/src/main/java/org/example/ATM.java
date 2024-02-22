package org.example;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
    private static HashMap<String, Account> accountHashMap = new HashMap<>();
    private static Scanner scan = new Scanner(System.in);
    protected static void openAccount(){
        System.out.println("Enter username :");
        String userName = scan.nextLine().toLowerCase();
        System.out.println("Open Account Deposit :");
        double startBalance = scan.nextDouble();
        scan.nextLine();

        Account account = new Account(userName, startBalance);
        accountHashMap.put(userName, account);
        System.out.println("Account has been successfully opened");
    }
    protected static void checkBalance(){
        System.out.println("Enter UserName");
        String user = scan.nextLine().toLowerCase();
        if (accountHashMap.containsKey(user)){
            Account account = accountHashMap.get(user);
            System.out.println("Available balance : R"+account.getBalance());
        }else {
            System.out.println("Account does not exist");
        }
    }
    protected static void deposit(){
        System.out.println("Enter UserName");
        String user = scan.nextLine().toLowerCase();
        if (accountHashMap.containsKey(user)){
            Account account = accountHashMap.get(user);
            System.out.println("Enter deposit amount");
            double amount = scan.nextDouble();
            scan.nextLine();
            if (amount <= 50){
                System.out.println("Deposit a minimum of R50.");
                return;
            }
            account.deposit(amount);
            System.out.println("successfully deposited R"+amount+" . New Balance R"+account.getBalance());
        }else {
            System.out.println("User does not exist");
        }
    }
    protected static void withdraw(){
        System.out.println("Enter UserName");
        String user = scan.nextLine().toLowerCase();
        if (accountHashMap.containsKey(user)) {
            Account account = accountHashMap.get(user);
            System.out.println("Enter deposit amount");
            double amount = scan.nextDouble();
            scan.nextLine();
            if (amount <= 0) {
                System.out.println("Withdraw amount above zero");
                return;
            }
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal Successful. New balance R" + account.getBalance());
            } else {
                System.out.println("Insufficient funds.");
            }
        }else {
            System.out.println("User does not exist");
        }
    }
    public static void run(){
        while (true){
            System.out.println("1. Open Account.");
            System.out.println("2. Check Balance.");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.println("Choose Option: ");
            int option = scan.nextInt();
            scan.nextLine();
            try {
                switch (option){
                    case 1:
                        openAccount();
                        break;
                    case 2:
                        checkBalance();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        withdraw();
                        break;
                    case 5:
                        System.out.println("Thank you for using our ATM. Goodbye!");
                        scan.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }

        }
    }
    public static void main(String[] args) {
        run();
    }
}
