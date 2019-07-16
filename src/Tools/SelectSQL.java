package Tools;

import java.sql.*;
import javax.swing.JOptionPane;

public class SelectSQL {
    ResultSet rset;
    String dataBaseUrl = Constants.dataBaseUrl + Constants.DatabaseName;//"jdbc:mysql://localhost:3306/sust_admission_result";
    DBConnection db = new DBConnection();
    Connection conn;
    Statement statement;
    
    public ResultSet selQuery(String sql) {
        
        try {            
            conn = db.dbConnect(dataBaseUrl, Constants.userName, Constants.passWord);
            statement = conn.createStatement();
            rset= statement.executeQuery(sql);
            
        } 
        catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Database Error! @ Search", "Error Message", JOptionPane.ERROR_MESSAGE);
            System.out.println("SelectSQL.java: SQL Exception occurred!!!");
            sqlException.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return rset;
    }
    
    public void close(){
        if(rset != null){
            try{
                rset.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        if(statement != null){
            try{
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        if(conn != null){
            try{
                conn.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    
    }
}
