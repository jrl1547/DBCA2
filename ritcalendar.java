import java.util.*;
import java.sql.*;

public class Ritcalendar{
   int term;
   String startDate;
   String addDropDeadline;
   String gradeDeadline;
   String endDate;
   Database db = new Database();
      
   public Ritcalendar(){
   }
   
   public Ritcalendar(int term){
      this.term = term;
   }
   
   public String getStartDate(){
      return startDate;
   }
   
   public void setStartDate(String startDate){
      this.startDate = startDate;
   }
   
   public String getAddDropDeadline(){
      return this.addDropDeadline;
   }
   
   public void setAddDropDeadline(String addDropDeadline){
      this.addDropDeadline = addDropDeadline;
   }
   
   public String getGradeDeadline(){
      return this.gradeDeadline;
   }
   
   public void setGradeDeadline(String gradeDeadline){
      this.gradeDeadline = gradeDeadline;
   }
   
   public String getEndDate(){
      return this.endDate;
   }
   
   public void setEndDate(String endDate){
      this.endDate = endDate;
   }
   
   /*
      Fetch gets the data regarding the rit calendar for the term that was given in the constructor.
   */
   public boolean fetch(){
      String select = "SELECT * FROM ritcalendar WHERE term = ?";
      ArrayList<String> args = new ArrayList<String>();
      args.add(""+this.term);
      ArrayList<ArrayList<String>> fetchData = db.getData(select, args);
      fetchData.remove(0);
      this.term            = fetchData.get(0).get(0).toString();
      this.startDate       = fetchData.get(0).get(1).toString();
      this.addDropDeadline = fetchData.get(0).get(2).toString();
      this.gradeDeadline   = fetchData.get(0).get(3).toString();
      this.endDate         = fetchData.get(0).get(4).toString();
   }
   
   /*
      update data for a currently existing term in the ritcalendar
   */
   public int put(){
      String update = "UPDATE ritcalendar SET startDate = ?, addDropDeadline = ?, gradeDeadline = ?, endDate = ? WHERE term = ?;";
      ArrayList<String> args = new ArrayList<String>();
      args.add(this.startDate);
      args.add(this.addDropDeadline);
      args.add(this.gradeDeadline);
      args.add(this.endDate);
      args.add(this.term);
      if(db.setData(update, args)){
         return 1;//success
      }
      return 0;//failure
   }
   
   /*
      insert all of the associated data into the database
   */
   public int post(){
      String insert = "INSERT INTO ritcalendar SET(term, startDate, addDropDeadline, gradeDeadline, endDate) VALUES(?,?,?,?,?);";
      ArrayList<String> args = new ArrayList<String>();
      args.add(this.term);
      args.add(this.startDate);
      args.add(this.addDropDeadline);
      args.add(this.gradeDeadline);
      args.add(this.endDate);
      if(db.setData(insert, args)){
         return 1;//success
      }
      return 0;//failure
   }
   
   /*
      delete all calendar data associated with the term given
   */
   public int delete(){
      String delete = "DELETE FROM ritcalendar WHERE term = ?;";
      ArrayList<String> args = new ArrayList<String>();
      args.add(ths.term);
      if(db.setData(delete, args)){
         return 1;//success
      }
      return 0;//failure
   }
}