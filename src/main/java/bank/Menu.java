package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
    private Scanner scan;

    public static void main(String[] args) {
        System.out.println("Welcom to Globe Bank International!");
        
        Menu menu = new Menu();
        menu.scan = new Scanner(System.in);

        Customer customer = menu.authenticateUser();

        if (customer != null) {
            Account account = DataSource.getAccount(customer.getAccountId());

            menu.showMenu(customer, account);
        }




        menu.scan.close();

    }

    private Customer authenticateUser(){
        Customer customer = null;
        
        System.out.println("Please enter your username: ");
        String username = scan.next();

        System.out.println("Please enter your password: ");
        String password = scan.next();
        try {
            customer = Authenticator.login(username, password);
        } catch (LoginException e) {
            // TODO: handle exception
            System.out.println("There was an error: "+e.getMessage());
        }

        return customer;
    }

    private void showMenu(Customer customer, Account acc){
        int select = 0;
        double amount = 0.0;

        while (select != 4 && customer.isAuthenticated()) {
            System.out.println("==============================================");
            System.out.println("Please select one of the following options: ");
            System.out.println("1: Deposit");
            System.out.println("2: Withdraw");
            System.out.println("3: Check Balance");
            System.out.println("4: Exit");
            System.out.println("==============================================");

            select = scan.nextInt();

            switch (select) {
                case 1:
                    System.out.println("How much you would like to deposit?");
                    amount = scan.nextDouble();
                    acc.deposit(amount);
                    break;
                case 2:
                    System.out.println("How much you would like to withdraw?");
                    amount = scan.nextDouble();
                    acc.withdraw(amount);
                    break;
                case 3:
                    System.out.println("Current balance: "+ acc.getBalance());
                    break;
                case 4:
                    Authenticator.logout(customer);
                    System.out.println("Thanks for banking at Globe Bank International!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

    }
}
