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
        

    }
}
