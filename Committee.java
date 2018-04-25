import java.util.*;

public class Committee{

   String username;
   String capstoneid;
   String has_accepted;
   String has_declined;
   String position;
   String tracking;
   Database capstone_project;
   
   /**********************************************************************************
   *                                   CONSTRUCTORS                                  *
   **********************************************************************************/

   public Committee(){
      capstone_project = new Database();
   }
   
   public Committee(String _capstoneid){
      capstone_project = new Database();
   }
   
   public Committee(String _username, String _capstoneid, String _has_accepted, String _has_declinded, String _position, String _tracking){
   
      capstone_project = new Database();
      
      username       = _username;
      capstoneid     = _capstoneid;
      has_accepted   = _has_accepted;
      has_declined   = _has_declinded;
      position       = _position;
      tracking       = _tracking;    
   }
   
   /**********************************************************************************
   *                                   ACCESSORS                                     *
   **********************************************************************************/
   
   String getUsername(){
      return username;
   } 
   
   String getCapstoneID(){
      return capstoneid;
   } 
   
   String getAccepted(){
      return has_accepted;
   } 
   
   String getDeclined(){
      return has_declined;
   } 
   
   String getPosition(){
      return position;
   } 
   
   String getTracking(){
      return tracking;
   } 
   
   
   
   /**********************************************************************************
   *                                   MUTATORS                                      *
   **********************************************************************************/
   void setUsername(String _username){
      username = _username;
   } 
   
   void setCapstoneID(String _capstoneid){
      capstoneid =  _capstoneid;
   } 
   
   void setAccepted(String _has_accepted){
      has_accepted =  _has_accepted;
   } 
   
   void setDeclined(String _has_declined){
      has_declined =  _has_declined;
   } 
   
   void setPosition(String _position){
      position =  _position;
   } 
   
   void setTracking(String _tracking){
      tracking = _tracking;
   }   
   
   
   


   /**********************************************************************************
   *                                   METHODS                                       *
   **********************************************************************************/
   
   public ArrayList<ArrayList<String>> fetch(String _capstoneid){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_capstoneid);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM committee WHERE capstoneid = ? AND username = ?;",item);
      fetchData.remove(0);
      
      capstoneid     = fetchData.get(0).get(0).toString();
      username       = fetchData.get(0).get(1).toString();
      has_accepted   = fetchData.get(0).get(2).toString();
      has_declined   = fetchData.get(0).get(3).toString();
      position       = fetchData.get(0).get(4).toString();
      tracking       = fetchData.get(0).get(5).toString();
      
      return fetchData;               
      
   }

   public ArrayList<ArrayList<String>> fetch(String _capstoneid, String _username){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_capstoneid);
      item.add(_username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM committee WHERE capstoneid = ? AND username = ?;",item);
      fetchData.remove(0);

      capstoneid     = fetchData.get(0).get(0).toString();
      username       = fetchData.get(0).get(1).toString();
      has_accepted   = fetchData.get(0).get(2).toString();
      has_declined   = fetchData.get(0).get(3).toString();
      position       = fetchData.get(0).get(4).toString();
      tracking       = fetchData.get(0).get(5).toString();

      return fetchData;

   }

   public ArrayList<ArrayList<String>> fetchRoles(String role){
      ArrayList<String> item = new ArrayList<String>();
         item.add(capstoneid);
         item.add(role);

      String query = "SELECT username, has_accepted FROM committee JOIN users USING (username) WHERE " +
              "capstoneid = ? AND positionid = ? AND has_declined != 1";

      ArrayList<ArrayList<String>> data = capstone_project.getData(query, item);
      if (data.isEmpty()){
         return data;
      } else {
         data.remove(0);
      }
      return data;
   }
   
   public boolean put(){
      ArrayList<String> item = new ArrayList<String>();
         item.add(username);
         item.add(has_accepted);
         item.add(has_declined);
         item.add(position);
         item.add(tracking);
         item.add(""+capstoneid);
      boolean put = capstone_project.setData("UPDATE committee SET username = ?, has_accepted = ?, has_declined = ?, positionid = ?, tracking = ? WHERE capstoneid = ?;", item);
      return put;
      
   }
   
   
   public boolean post(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      item.add(capstoneid);
      item.add(has_accepted);
      item.add(has_declined);
      item.add(position);
      item.add(tracking);
      
      boolean post = capstone_project.setData("INSERT INTO `cap`.`committee` (`username`, `capstoneid`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (?, ?, ?, ?,?,?)", item);
      return post;
   }
   
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(""+capstoneid);
      boolean delete = capstone_project.setData("DELETE FROM committee WHERE capstoneid = ?;",item);
      return delete;
   }

   public ArrayList<ArrayList<String>> getAcceptedCapstones(String _username){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData(
              "SELECT capstone.title, users.fullname, capstone.username, capstone.abstract, capstone.grade FROM capstone\n" +
              "JOIN users ON capstone.username = users.username\n" +
              "JOIN committee ON capstone.capstoneid = committee.capstoneid\n" +
              "WHERE committee.username = ? AND has_accepted = true;",item);
      fetchData.remove(0);
      
      return fetchData;               
      
   }
   
   public ArrayList<ArrayList<String>> getInvitedCapstones(String _username){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT capstone.title, users.fullname, capstone.username, capstone.abstract, capstone.grade FROM capstone\n" +
              "JOIN users ON capstone.username = users.username\n" +
              "JOIN committee ON capstone.capstoneid = committee.capstoneid\n" +
              "WHERE committee.username = ? AND committee.has_accepted = false AND committee.has_declined = false AND committee.positionid != '1';",item);
      fetchData.remove(0);
      
      return fetchData;               
      
   }

   public ArrayList<ArrayList<String>> getTrackedCapstones(String _username){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT capstone.title, users.fullname, users.username, capstone.abstract, capstone.plagerismscore, capstone.grade FROM capstone\n" +
              "JOIN users ON capstone.username = users.username\n" +
              "JOIN committee ON capstone.capstoneid = committee.capstoneid\n" +
              "WHERE committee.username = ? AND committee.tracking = 1;", item);
      fetchData.remove(0);
      return fetchData;
   }


   public ArrayList<ArrayList<String>> getCommitteeFaculty(){
           ArrayList<String> item = new ArrayList<String>();
           item.add("1");
           ArrayList<ArrayList<String>> fetchData = capstone_project.getData("select committee.username from capstone join committee where capstone.capstoneid = committee.capstoneid  and has_accepted = ?;",item);
            fetchData.remove(0);
                  return fetchData;

   }
 public ArrayList<ArrayList<String>> getDeclinedCommitteeFaculty(){
           ArrayList<String> item = new ArrayList<String>();
           item.add("1");
           ArrayList<ArrayList<String>> fetchData = capstone_project.getData("select committee.username from capstone join committee where capstone.capstoneid = committee.capstoneid  and has_declined = ?;",item);
            fetchData.remove(0);
                  return fetchData;

   }


}