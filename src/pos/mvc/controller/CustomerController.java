/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.controller;
import pos.mvc.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pos.mvc.db.DbConnection;
import java.util.List;

/**
 *
 * @author kavindu
 */
public class CustomerController {
    public String addCustomer(Customer customer) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        
        String query = "INSERT INTO Customer VALUES(?,?,?,?,?) ";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPostalCode());
        
        if(preparedStatement.executeUpdate()>0){
            return "Success";
        }else{
            return "Fail";
        }
        
    }
    
    public List<Customer> getCustomer() throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT *FROM Customer";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        List<Customer> customers = new ArrayList<>();
        
        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getInt("id"), 
                    resultSet.getString("name"), 
                    resultSet.getString("email"), 
                    resultSet.getString("address"), 
                    resultSet.getString("postalCode"));
            customers.add(customer);
        }
        
        return customers; 
    }
    
    public Customer getCustomer(Integer id) throws SQLException{
         Connection connection =  DbConnection.getInstance().getConnection();
         String query = "SELECT *FROM Customer WHERE id =?";
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         preparedStatement.setInt(1, id);
         ResultSet resultSet = preparedStatement.executeQuery();
         
         while (resultSet.next()) {
            return new Customer(
                    resultSet.getInt("id"), 
                    resultSet.getString("name"), 
                    resultSet.getString("email"), 
                    resultSet.getString("address"), 
                    resultSet.getString("postalCode"));
         }
         return null;
         
    }
    
    public String updateCustomer (Customer customer) throws SQLException{
        Connection connection =  DbConnection.getInstance().getConnection();
        String query ="UPDATE Customer SET id=?, name=?, address=?, email=?, postalCode=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPostalCode());
        
        preparedStatement.setInt(6, customer.getId());
        
        if(preparedStatement.executeUpdate()>0){
            return "Success";
        }else{
            return "Fail";
        }
        

    }
    
    public String deleteCustomer(int id) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "DELETE FROM Customer WHERE id = ? ";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        
        int row = preparedStatement.executeUpdate();
        return "Deleted Succesfully";
       
    }
}
