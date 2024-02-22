package org.example;

public class Account {
    private String userName;
    private double balance;

    public Account(String userName, double balance){
        this.balance = balance;
        this.userName = userName;
    }
    public String getUserName(){
        return  userName;
    }
    public double getBalance(){
        return balance;
    }
    public void deposit(double amount){
        balance += amount;
    }
    public boolean withdraw(double amount){
        if (amount <= balance){
            balance -= amount;
            return true;
        }else {
            return false;
        }
    }
}
