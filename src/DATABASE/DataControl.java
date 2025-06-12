/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATABASE;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class DataControl implements AutoCloseable {
    String dbname = "pos_updated";
    String url = "jdbc:mysql://localhost:3306/" + dbname;
    Connection conn;
    Statement statement;
    PreparedStatement preparedStatement;
    
    public DataControl() throws SQLException{
        this.conn = DriverManager.getConnection(url, "root", "");
        this.statement = conn.createStatement();
    }
    
    public Boolean verifyLogin(String name, String pass) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND pass = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, pass);
            ResultSet res = ps.executeQuery();

            if (res.next()) {
                String role = res.getString("role");
                if ("admin".equalsIgnoreCase(role)) {
                    return true;
                } else if ("user".equalsIgnoreCase(role)) {
                    return false;
                }
            }
        }
        return null; // no user found
    }

    public ArrayList<String> getIncome() throws SQLException{
        String query = "SELECT SUM(income) as total_income, income_date AS date FROM `transactions` GROUP BY income_date;";
        ResultSet res = statement.executeQuery(query);
        ArrayList<String> datas = new ArrayList<>();
        while(res.next()) {
            datas.add(res.getString("total_income"));  
            datas.add(res.getString("date")); 
        }
        return datas;
    }
    public void saveIncome(int income) throws SQLException{
        LocalDate date = LocalDate.now();
        String query = "INSERT INTO transactions (income, income_date) VALUES (?, ?);";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, income);
        preparedStatement.setDate(2, java.sql.Date.valueOf(date));
        preparedStatement.executeUpdate(); 
    }
    public void insertUser(String name, String pass) throws SQLException{
        String query = "INSERT INTO users (role, username, pass) VALUES (?, ?, ?);";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, "user");
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, pass);
        preparedStatement.executeUpdate(); 
    }
    public ArrayList<ArrayList<String>> getAccounts() throws SQLException {
        String query = "SELECT user_id, username, role FROM users";
        ResultSet res = statement.executeQuery(query);
        ArrayList<ArrayList<String>> datas = new ArrayList<>();

        while(res.next()) {
            ArrayList<String> dataset = new ArrayList<>(); 
            dataset.add(Integer.toString(res.getInt("user_id")));  
            dataset.add(res.getString("username"));  
            dataset.add(res.getString("role"));
            datas.add(dataset);
        }
        return datas;
    }
    public void updateUser(String userId, String newUsername, String newRoleId) throws SQLException {
        String query = "UPDATE users SET role = ?, username = ? WHERE user_id = ?;";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, newRoleId);
        preparedStatement.setString(2, newUsername);
        preparedStatement.setInt(3, Integer.parseInt(userId));
        int rows = preparedStatement.executeUpdate();
        System.out.println("rows affected"  + rows);
        System.out.println(conn.getCatalog());
    }
     public void deleteUser(String userId) throws SQLException {
        String query = "DELETE FROM users WHERE user_id = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, Integer.parseInt(userId));
        preparedStatement.executeUpdate();
    }
    @Override
    public void close() throws SQLException {
        if(statement != null && !statement.isClosed()){
            statement.close();
        }
        if(preparedStatement != null && !preparedStatement.isClosed()){
            preparedStatement.close();
        }
        if(conn != null && !conn.isClosed()){
            conn.close();
        }
    }
}
