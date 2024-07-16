package com.pms.utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {
    
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Sales_Manager;trustServerCertificate=true";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "hieu26102005";
    
    public static void connectDb(){
        
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PreparedStatement PrepareStatement(String sql, Object... o) throws SQLException{
        PreparedStatement ps = null;
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        try {
            if(sql.trim().startsWith("{")){
                ps = connection.prepareCall(sql);
            } else {
                ps = connection.prepareStatement(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i = 0 ; i < o.length ; i++){
            ps.setObject(i+1, o[i]);
        }
        
        return ps;
    }
    
    public static int update(String sql, Object... o){
        try {
            PreparedStatement ps = PrepareStatement(sql, o);
            try {
                return ps.executeUpdate();
            } finally {
                ps.getConnection().close();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static ResultSet query(String sql, Object... o){
        try {
            try {
                return PrepareStatement(sql, o).executeQuery();
            } finally {
                PrepareStatement(sql, o).getConnection().close();
            } 
        } catch (SQLException e) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE,null, e);
        }
        return null;
    }
}
