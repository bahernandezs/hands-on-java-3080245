package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
    public static Connection connect(){
        String db_file = "jdbc:sqlite:hands-on-java-3080245\\resources\\bank.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db_file);
            System.out.println("We're connected");
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return connection;
    }

    public static Customer getCustomer(String username) {
        String sql = "select * from customers where username = ?";
        Customer customer = null;
        try (
            Connection cnn = connect();
            PreparedStatement statement = cnn.prepareStatement(sql)
        ) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery();) {
                customer = new Customer(
                    resultSet.getInt("id"), 
                    resultSet.getString("name"), 
                    resultSet.getString("username"), 
                    resultSet.getString("password"), 
                    resultSet.getInt("account_id"));
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return customer;
    }

    public static Account getAccount(int accountID){
        String sql = "Select * from Accounts where id = ?";
        Account acc = null;
        try (
            Connection cnn = connect();
            PreparedStatement statement = cnn.prepareStatement(sql)
        ) {
            statement.setInt(1, accountID);
            try (ResultSet resultSet = statement.executeQuery()) {
                acc = new Account(
                    resultSet.getInt("id"), 
                    resultSet.getString("type"), 
                    resultSet.getInt("balance"));
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return acc;
    }

    public static void updateAccountBalance(int accountId, double balance){
        String sql = "update accounts set balance = ? where id = ?";

        try (
            Connection cnn = connect();
            PreparedStatement statement = cnn.prepareStatement(sql)
        ) {
            statement.setDouble(1, balance);
            statement.setInt(2, accountId);

            statement.executeUpdate();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
