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
      System.out.println(fetchData);
      return fetchData;               
      
   }
   
   public ArrayList<ArrayList<String>> listRoles(){
      ArrayList<String> item = new ArrayList<String>();
      item.add("%");
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT role FROM roles WHERE role LIKE ?;",item);
      for(ArrayList<String> rol : fetchData){
         rol.removeAll(Arrays.asList("role"));
      }
      return fetchData;    
   }
   
   public String getRoleId(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(role);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT roleid FROM roles WHERE role = ?;",item);
      item.remove(0);
      item.add(fetchData.get(1).get(0));
      return item.get(0);
   }
   
   public String getRoleId(String _role){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_role);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT roleid FROM roles WHERE role = ?;",item);
      item.remove(0);
      item.add(fetchData.get(1).get(0));
      return item.get(0);
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