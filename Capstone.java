import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

public class Capstone{


   private String capstoneid, title, desc, plagerismscore, grade, type, defensedate, username;
   private Database capstone_project;


   
   /**********************************************************************************
   *                                   CONSTRUCTORS                                  *
   **********************************************************************************/
   
   public Capstone(){
      capstone_project = new Database();
   }
   
   public Capstone(String _username){
      capstone_project = new Database();
      username  = _username;
   }
   
   public Capstone(String _username, String _title, String _desc, String _plagerismscore, String _grade, String _type){
      capstone_project = new Database();
      username       = _username;
      title          = _title;
      desc           = _desc;
      plagerismscore = _plagerismscore;
      grade          = _grade;
      type           = _type;
   }

   //Create initial Capstone
   public Capstone(String title, String username, String type, String description, String defensedate){
       this();
      this.title = title;
      this.username = username;
      this.type = type;
      this.desc = description;
      this.defensedate = defensedate;
      postInit();
   }
   
   
   
   /**********************************************************************************
   *                                   ACCESSORS                                     *
   **********************************************************************************/
   
   String getCapstoneID(){
      return capstoneid;
   }
   
   String getTitle(){
      return title;
   }
   
   String getUsername(){
      return username;
   }
   
   String getDesc(){
      return desc;
   }
   
   String getPlagerismscore(){
      return plagerismscore;
   }
   
   String getGrade(){
      return grade;
   }
   
   String getType(){
      return type;
   }
   
   String getDefensedate(){
      return defensedate;
   }

   /**********************************************************************************
   *                                   MUTATORS                                      *
   **********************************************************************************/
      
   void setCapstoneID(String _capstoneid){
      capstoneid = getCapstoneId(username);
   }
   
   void setTitle(String _title){
      title = _title;
   }
   
   void setDesc(String _desc){
      desc = _desc;
   }
   
   void setPlagerismscore(String _plagerismscore){
      plagerismscore = _plagerismscore;
   }
   
   void setGrade(String _grade){
      grade = _grade;
   }
   
   void setType(String _type){
      type = _type;
   }
   
   void setDefensedate(String _defensedate){
      defensedate = _defensedate;
   }


   /**********************************************************************************
   *                                   METHODS                                       *
   **********************************************************************************/   
   
   public ArrayList<ArrayList<String>> fetch(String _capstoneid) {
      ArrayList<String> item = new ArrayList<String>();
      item.add(_capstoneid);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM capstone WHERE capstoneid = ?;", item);

      if (!fetchData.isEmpty()) {
         capstoneid = fetchData.get(1).get(0);
         username = fetchData.get(1).get(1);
         type = fetchData.get(1).get(2);
         title = fetchData.get(1).get(3);
         desc = fetchData.get(1).get(4);
         plagerismscore = fetchData.get(1).get(5);
         grade = fetchData.get(1).get(6);

      }
      return fetchData;
   }
   
   public boolean put(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(title);
      item.add(desc);
      item.add(plagerismscore);
      item.add(grade);
      item.add(type);
      item.add(""+capstoneid);
      boolean put = capstone_project.setData("UPDATE capstone SET title = ?, desc = ?, plagerismscore = ?, grade = ?, type = ? WHERE capstoneid = ?;", item);
      return put;
      
   }
   
   //unneccary with postInit?
   public boolean post(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(""+capstoneid);
      item.add(title);
      item.add(desc);
      item.add(plagerismscore);
      item.add(grade);
      item.add(type);
      item.add(defensedate);
      boolean post = capstone_project.setData("INSERT INTO `capstone_project`.`capstone` (`capstoneid`, `title`, `abstrac`, `plagerismscore`, `grade`, `typeid`, `defensedate`) VALUES (?,?,?,?,?,?,?);", item);
      return post;
   }

   public boolean postInit(){
      ArrayList<String> item = new ArrayList<String>();
          item.add(username);
          item.add(type);
          item.add(title);
          item.add(desc);

      boolean bool = capstone_project.setData("INSERT INTO capstone (username, typeid, title, abstract) VALUES (?,?,?,?);", item);
      StudentDetails student = new StudentDetails();
         student.fetch(username);
         capstoneid = student.getCapstoneId();

      return bool;
   }
   
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(""+capstoneid);
      boolean delete = capstone_project.setData("DELETE FROM capstone WHERE capstoneid = ?;",item);
      return delete;
   }
   
   public String getCapstoneId(String _username){
   
      ArrayList<String> item = new ArrayList<String>();
      item.add(_username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM capstone WHERE username = ?;",item);
      if (!fetchData.isEmpty()) {
         capstoneid = fetchData.get(1).get(0);

      }
      return capstoneid;
   }

   public ArrayList<ArrayList<String>> getView(String _username){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("select capstone.title, users.fullname, capstone.username, capstone.abstract," +
              "statushistory.date, capstone.plagerismscore, capstone.grade FROM capstone\n" +
              "JOIN users ON capstone.username = users.username\n" +
              "JOIN statushistory ON capstone.capstoneid = statushistory.capstoneid\n" +
              "WHERE capstone.username = ?;",item);
      fetchData.remove(0);
      return fetchData;

   }
     
   public ArrayList<ArrayList<String>> getCapstonesStaff(){
           ArrayList<String> item = new ArrayList<String>();
           item.add("-1");
           ArrayList<ArrayList<String>> fetchData = capstone_project.getData("select users.fullName,capstone.title,capstone.abstract,capstone.plagerismscore,capstone.grade FROM capstone JOIN users ON capstone.username = users.username WHERE( capstone.title != ?);",item);
            fetchData.remove(0);
                  return fetchData;

   }
    public ArrayList<ArrayList<String>> getCapstonesStatus(){
           ArrayList<String> item = new ArrayList<String>();
           item.add("-1");
           ArrayList<ArrayList<String>> fetchData = capstone_project.getData("select statusid from statushistory join capstone on capstone.capstoneid= statushistory.capstoneid WHERE(capstone.capstoneid != ?);",item);
           fetchData.remove(0);
                  return fetchData;

   }

    public ArrayList<ArrayList<String>> getStudentHistoryName(String _username){
       ArrayList<String> item = new ArrayList<String>();
       item.add(_username);
       ArrayList<ArrayList<String>> fetchData = capstone_project.getData("select username from capstone WHERE username = ?;",item);
       fetchData.remove(0);
       return fetchData;
   }

   public ArrayList<ArrayList<String>> getCapstonesStatusByName(String _username){
       ArrayList<String> item = new ArrayList<String>();
       item.add(_username);
       ArrayList<ArrayList<String>> fetchData = capstone_project.getData("select statusid from statushistory join capstone on capstone.capstoneid= statushistory.capstoneid WHERE capstone.username = ? ;",item);
       fetchData.remove(0);
       return fetchData;

   }

}
   
