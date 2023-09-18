/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.controller;
import java.sql.Connection;
import java.sql.SQLException;
import pos.mvc.db.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import pos.mvc.model.Item;
/**
 *
 * @author kavindu
 */
public class ItemController {
    public String addItem(Item item) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        
        String query ="INSERT INTO Item Values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setInt(3, item.getQuantityOnHand());
        preparedStatement.setDouble(4, item.getUnitPrice());
        
        if(preparedStatement.executeUpdate()>0){
            return "Success";
        }else{
            return "Fail";
        }
        
        
    }
    public List<Item> getItem() throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT *FROM Item";
        PreparedStatement preparedstatement = connection.prepareStatement(query);
        
        ResultSet resultSet = preparedstatement.executeQuery();
        
        List<Item> items = new ArrayList<>();
        
        while(resultSet.next()){
            Item item = new Item(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("quantityOnHand"),
                    resultSet.getDouble("unitPrice"));
            items.add(item);
        }
        return items;
        
    }
    
    public Item getItem(Integer id) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT *FROM Item WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            return new Item(
                 resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("quantityOnHand"),
                    resultSet.getDouble("unitPrice") 
            );
        }
        return null;
        
    }
    
    public String updateItem(Item item) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "UPDATE Item SET id=?, name=?,quantityOnHand=?,unitPrice=? WHERE id=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setInt(3, item.getQuantityOnHand());
        preparedStatement.setDouble(4, item.getUnitPrice());
        preparedStatement.setDouble(5, item.getId());
        
        if(preparedStatement.executeUpdate()>0){
            return "Success";
        }else{
            return "Fail";
        }
    }
    
    public String deleteItem(int id) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "DELETE FROM Item WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int row = preparedStatement.executeUpdate();
        return "Deleted Succesfully";
    }
    
}
