package Tools;

import java.sql.*;


public class InUpDelSQL {
    String dataBaseUrl = Constants.dataBaseUrl + Constants.DatabaseName; //sust_admission_result";
    DBConnection db = new DBConnection();
    Connection conn;
    Statement statement;
    
    public void inUpQuery(String inUp, String sql) {
        try {            
            //System.out.println("URL:"+dataBaseUrl);            
            conn = db.dbConnect(dataBaseUrl, Constants.userName, Constants.passWord);
            statement = conn.createStatement();

            if (inUp.equals("insert") || inUp.equals("delete")) {
                statement.execute(sql);
            }
            else { //'update'
                statement.executeUpdate(sql); 
            }
        }
        catch (SQLException sqlException) {
            //JOptionPane.showMessageDialog(null, "Fill Up * field correctly. ", "Error Message", JOptionPane.ERROR_MESSAGE);
            System.out.println("InUpDelSQL.java : SQLException occurred in inUpQuery() method!!!\n"+sqlException.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            this.close();
        }
    }
    
    //Modified with exception :: 20170527
    public void inUpQuery1(String inUp, String sql) throws Exception {
        try {            
            //System.out.println("URL:"+dataBaseUrl);            
            conn = db.dbConnect(dataBaseUrl, Constants.userName, Constants.passWord);
            statement = conn.createStatement();

            if (inUp.equals("insert") || inUp.equals("delete")) {
                statement.execute(sql);
            }
            else { //'update'
                statement.executeUpdate(sql); 
            }        
        }
        finally{
            this.close();
        }
    }

    private void close(){
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

