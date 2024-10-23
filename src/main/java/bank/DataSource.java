package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
    public static Connection connect(){
        String db_file = "jdbc:sqlite:resources/bank.db";
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
}
