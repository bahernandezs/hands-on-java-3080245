package bank;

import bank.exceptions.AmountException;

public class Account {
    
    private int id;
    private String type;
    private double balance;

    public Account(int id, String type, double balance){
        setId(id);
        setType(type);
        setBalance(balance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) throws Exception{

        if (amount < 1){
            throw new AmountException("The minimum deposit is 1.00 ");
        } else {
            double newBalance = balance + amount;
            setBalance(newBalance);
            
            
        }

        

    }

    public void withdraw(double amount){
        if (amount > 0) {
            balance -= amount;
        }
    }

}
