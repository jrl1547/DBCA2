import java.sql.*;
import java.util.*;

/**
*  @author     Jason Lamb
*  DBCA - Database
*
*  The purpose of this class is to do the basic functionality of a Database - connect, close, query, and manipulate the database.
*
*
*/

public class Database{

   Connection conn = null;
   String uri = "jdbc:mysql://localhost/cap?autoReconnect=true&useSSL=false";
      //"jdbc:mysql://localhost/capstone_project?autoReconnect=true&useSSL=false";
   String driver = "com.mysql.jdbc.Driver";      
   String user = "root";
   String password = "password";//"student";
   Statement smt = null;


   /**
   * The purpose of connect is to take the connection object and attempt a connection to the database.
   * @return true if connection works
   * @return false if connection fails
   * @throws SQLException
   * @throws ClassNotFoundException
   */
   public boolean connect(){
   
      try {
         Class.forName(driver); 
         conn = DriverManager.getConnection( uri, user, password );
         return true;
      }
      catch( SQLException sqle){
         sqle.printStackTrace();
         return false;
      }
      catch( ClassNotFoundException cnfe){
         cnfe.printStackTrace();
         return false;
      }
   }//end connect

   /**
   * The purpose of close is to take the connection object and attempt to close it.
   * @return true if close works
   * @return false if close fails
   * @throws SQLException
   */
   public boolean close(){
      try{
         conn.close();
         return true;
      }
      catch( Exception sqle){
         return false;
      }
   }//end close

   /**
   * The purpose of prepare is to take the connection object and attempt to close it.
   * @return PreparedStatement object holding the prepared statement. 
   * @throws SQLException
   *
   */

   public PreparedStatement prepare(String SQL, ArrayList<String> values){
      
      connect();
            
         PreparedStatement ps = null;
         try{
            ps = conn.prepareStatement(SQL);
            int i = 1;
            for(String val : values){
               ps.setString(i, val);
               i++;
            
            }
         }
         catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
         }
         return ps;
   }
   /**
   * The purpose of getData is to take a SQL string, an arraylist of String values, 
   * @return ArrayList<ArrayList<String>> object holding a 2D-ArrayList of the values. 
   * @see prepare    
   * @throws SQLException
   */
   
   public ArrayList<ArrayList<String>> getData(String SQL, ArrayList<String> values){
   
      ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
      PreparedStatement ps = prepare(SQL, values);
      
      try{
         ArrayList<String> name = new ArrayList<String>();
         ResultSet rs = ps.executeQuery();
         ResultSetMetaData rsmd = rs.getMetaData();
         int len = rsmd.getColumnCount();
         
         rs.beforeFirst();
         while(rs.next()){
         
            ArrayList<String> row = new ArrayList<String>();
            
            for(int i=1;i<=len;i++){
               name.add(rsmd.getColumnName(i));
            }
            data.add(name);
            
            for (int i = 1; i <= len; i++) {
               row.add(rs.getString(i));
            }
            data.add(row);
         }
         
      }
      catch(SQLException  sqle){
         return null;
      }
      close();
      return data;
   }

   /*
      Just a function to return a result set from the data layer if it needs to be prepared
    */
   public ResultSet getResultSet(String SQL, ArrayList<String> values){
      PreparedStatement ps = prepare(SQL, values);
      ResultSet rs;
      try{
         rs = ps.executeQuery();
      }
      catch(SQLException  sqle){
         return null;
      }
      close();
      return rs;
   }


   /*
   Function to get result set if it doesn't need preparing
    */
   public ResultSet getResultSetSelect(String SQL){
      ResultSet rs = null;
      try{
         Statement st = conn.createStatement();
         rs = st.executeQuery(SQL);
      }
      catch(SQLException sqle){
         sqle.printStackTrace();
      }
      close();
      return rs;
   }
   
   /**
   * The purpose of executeStmt is to take a SQL string, an arraylist of String values, 
   * @return int value which is number of records that were updated. 
   *  @see prepare
   *
   */
   public int executeStmt(String SQL, ArrayList<String> values){
   
      int row = -1;
   
      PreparedStatement test = prepare(SQL, values);
      try{
         row = test.executeUpdate();
      }
      catch(SQLException sqle){
         sqle.printStackTrace();
         return row;
      }
      
      return row;
   }
   
   /**
   * The purpose of setData is to take a SQL string, an arraylist of String values, 
   * @return boolean value if there was more than one record set. 
   *  @see executeStmt
   *
   */
   public boolean setData(String SQL, ArrayList<String> values){
      int exc = executeStmt(SQL, values);
      
      if(exc > -1){
         close();
         return true;
      }
      else{
         close();
         return false;
      }
   }
}