package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    public Message createMessage(int posted_by, String message_text, long time_posted_epoch) { 
        /*
        create table message (
        message_id int primary key auto_increment,
        posted_by int,
        message_text varchar(255),
        time_posted_epoch bigint,
        foreign key (posted_by) references  account(account_id)
        );      
        */
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, posted_by);
            ps.setString(2, message_text);
            ps.setLong(3, time_posted_epoch);
            ps.executeUpdate();
            ResultSet pkeyResultSet = ps.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, posted_by, message_text, time_posted_epoch);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Message> getAllMessages() { 
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                messages.add(new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch")));
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }
    public Message getMessageById(int message_id) { 
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, message_id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean deleteMessageById(int message_id) { 
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, message_id);
            
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateMessage(int message_id, String message_text) { 
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE account SET message_text = ? WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, message_text);
            ps.setInt(2, message_id);
            
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public List<Message> getMessagesByUserId(int account_id) { 
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, account_id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                messages.add(new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch")));
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }
    
}
