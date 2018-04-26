import java.util.*;

public class Status{

   String sid;
   String name;
   String stepcode;
   String description;
   Database capstone_project;


   /**********************************************************************************
   *                                   CONSTRUCTORS                                  *
   **********************************************************************************/
   
   public Status(){
      capstone_project = new Database();
   }
   
   public Status(String _sid){
      sid = _sid;
      capstone_project = new Database();
   
   }
   
   public Status(String _sid, String _name, String _stepcode, String _description){
   
      capstone_project = new Database();
      
      
      sid         = _sid;
      name        = _name;
      stepcode    = _stepcode;
      description = _description;
   
   }
   
   /**********************************************************************************
   *                                   ACCESSORS                                     *
   **********************************************************************************/
   
   String getSID(){
      return sid;
   }
   
   String getName(){
      return name;
   }
   
   String getStepcode(){
      return stepcode;
   }
   
   String getDescription(){
      return description;
   }
   
   /**********************************************************************************
   *                                   MUTATORS                                      *
   **********************************************************************************/
   
   void setSID(String _sid){
      sid = _sid;
   }
   
   void setName(String _name){
      name = _name;
   }
   
   void setStepcode(String _stepcode){
      stepcode = _stepcode;
   }
   
   void setDescription(String _description){
      description = _description;
   }
   
   /**********************************************************************************
   *                                   METHODS                                       *
   **********************************************************************************/
   public ArrayList<ArrayList<String>> fetch(String _sid){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_sid);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM status WHERE statusid = ?;",item);
      fetchData.remove(0);
      
      sid         = fetchData.get(0).get(0);
      name        = fetchData.get(0).get(1);
      stepcode    = fetchData.get(0).get(2);
      description = fetchData.get(0).get(3);
      
      return fetchData;               
      
   }
   
   public boolean put(){
      ArrayList<String> item = new ArrayList<String>();
       
      item.add(name);
      item.add(stepcode);
      item.add(description);
      item.add(sid);
      boolean put = capstone_project.setData("UPDATE status SET name = ?, stepcode = ?, description = ? WHERE sid = ?;", item);
      return put;
      
   }
   
   
   public boolean post(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(sid);
      item.add(name);
      item.add(stepcode);
      item.add(description);
      
      boolean post = capstone_project.setData("INSERT INTO `capstone_project`.`status` (`sid`, `name`, `stepcode`, `description`) VALUES (?,?,?,?);", item);
      return post;
   }
   
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(sid);
      boolean delete = capstone_project.setData("DELETE FROM status WHERE sid = ?;",item);
      return delete;
   }

   public ArrayList<ArrayList<String>> fetchStatusID(String _status){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_status);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM status WHERE name = ?;",item);
      fetchData.remove(0);

      sid         = fetchData.get(0).get(0);
      name        = fetchData.get(0).get(1);
      stepcode    = fetchData.get(0).get(2);
      description = fetchData.get(0).get(3);

      return fetchData;

   }
    public ArrayList<ArrayList<String>> getTypes(){
        String query = "SELECT statusid,name FROM status";
        return capstone_project.getData(query);
    }


}