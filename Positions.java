import java.util.*;

public class Positions{


   private String positionid, position;
   private Database capstone_project;
   
  public Positions(){
      capstone_project = new Database();
   }
   
   public Positions(String _position){
      capstone_project = new Database();
      position  = _position;
   }  
   
   
   public ArrayList<ArrayList<String>> fetch(){
      ArrayList<String> item = new ArrayList<String>();
      item.add("%");
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM positions like ?;",item);
      positionid   = fetchData.get(1).get(0).toString();
      position     = fetchData.get(1).get(1).toString();
      return fetchData;               
      
   }
   
   public boolean put(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(position);
      item.add(positionid);
      boolean put = capstone_project.setData("UPDATE roles SET role = ? WHERE roleid = ?;", item);
      return put;
   }
   
   public boolean post(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(position);
      return capstone_project.setData("INSERT INTO types(role) VALUES (?);", item);

   }
   
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(positionid);
      boolean delete = capstone_project.setData("DELETE FROM types WHERE roleid = ?;",item);
      return delete;
   }
   
}