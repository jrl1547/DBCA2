import java.util.*;

public class Types{


   private String typeid, type;
   private Database capstone_project;
   
  public Types(){
      capstone_project = new Database();
   }
   
   public Types(String _type){
      capstone_project = new Database();
      type  = _type;
   }  
   
   
   public ArrayList<ArrayList<String>> fetch(String _capstoneid){
      ArrayList<String> item = new ArrayList<String>();
      item.add("%");
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM types like ?;",item);
      typeid   = fetchData.get(1).get(0).toString();
      type     = fetchData.get(1).get(1).toString();
      return fetchData;               
      
   }
   
   public boolean put(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(type);
      item.add(typeid);
      boolean put = capstone_project.setData("UPDATE types SET type = ? WHERE typeid = ?;", item);
      return put;
   }
   
   public boolean post(){
      ArrayList<String> item = new ArrayList<String>();
          item.add(type);
      return capstone_project.setData("INSERT INTO types(type) VALUES (?);", item);

   }
   
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(typeid);
      boolean delete = capstone_project.setData("DELETE FROM types WHERE typeid = ?;",item);
      return delete;
   }
   
}