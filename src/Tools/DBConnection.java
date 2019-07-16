package Tools;
import java.sql.*;

public class DBConnection
{
//    private String dbConnectString;
//    private String dbUserId;
//    private String dbPassword;
    
    public DBConnection() {
    }

    public Connection dbConnect(String dbConnectString, String dbUserId, String dbPassword) throws Exception{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(dbConnectString, dbUserId, dbPassword);
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("DBConnection.java : Exception Occurred in dbConnect method!!!");
            //return null;
        }
    }
}
