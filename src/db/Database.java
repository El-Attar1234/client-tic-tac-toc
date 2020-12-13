/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;


/**
 *
 * @author Mohamed
 */
public class Database {

    private static Database instance;
    private  static Connection con;

    public Database() {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/HistoryRecords", "menna","menna");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }
    
    

    
    public void insert(String Player1Name,String player2Name,String Player1seq,
                            String player2seq,String firstPlayer,String theWinner) {
        String d="dddd";
       
        try {
            PreparedStatement pst= con.prepareStatement("insert into Record Values (?,?,?,?,?,?,?)");
            
            pst.setString(1, Player1Name);
            pst.setString(2, player2Name);
            pst.setString(3, Player1seq);
            pst.setString(4, player2seq);
            pst.setString(5, theWinner);
            pst.setString(6, d);
            pst.setString(7, firstPlayer);
           
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
        
}
