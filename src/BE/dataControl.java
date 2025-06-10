/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BE;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author HP
 */
public class dataControl {
    String dbname = "pos_updated";
    String url = "jdbc:mysql://localhost:3306/" + dbname;
    Connection conn;
    Statement stm;
    
    public dataControl() throws SQLException{
        this.conn = DriverManager.getConnection(url, "root", "");
        this.stm = conn.createStatement();
    }
    
    public boolean verifyLogin(String name, String pass) throws SQLException{
        String query = "SELECT * FROM users WHERE username = '" + name + "' AND pass = '" + pass + "';";
        ResultSet res = stm.executeQuery(query);
        boolean isAdmin = false;
        if(res.next()){
            isAdmin = "admin".equals(res.getString("role"));
        }
        return isAdmin;
    }
}
