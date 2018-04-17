import java.util.*;

public class Roles{


   private String roleid, role;
   private Database capstone_project;
   
  public Roles(){
      capstone_project = new Database();
   }
   
   public Roles(String _role){
      capstone_project = new Database();
      role  = _role;
   }  
   
   
   public ArrayList<ArrayList<String>> fetch(){
      ArrayList<String> item = new ArrayList<String>();
      item.add("%");
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM roles like ?;",item);
      roleid   = fetchData.get(1).get(0).toString();
      role     = fetchData.get(1).get(1).toString();
      return fetchData;               
      
   }
   
   public boolean put(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(role);
      item.add(roleid);
      boolean put = capstone_project.setData("UPDATE roles SET role = ? WHERE roleid = ?;", item);
      return put;
   }
   
   public boolean post(){
      ArrayList<String> item = new ArrayList<String>();
          item.add(role);
      return capstone_project.setData("INSERT INTO types(role) VALUES (?);", item);

   }
   
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(roleid);
      boolean delete = capstone_project.setData("DELETE FROM types WHERE roleid = ?;",item);
      return delete;
   }
   
}