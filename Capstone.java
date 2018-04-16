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
   
   public Capstone(String _capstoneid){
      capstone_project = new Database();
      capstoneid  = _capstoneid;
   }
   
   public Capstone(String _capstoneid, String _title, String _desc, String _plagerismscore, String _grade, String _type, String _defensedate){
      capstone_project = new Database();
      
      capstoneid     = _capstoneid;
      title          = _title;
      desc           = _desc;
      plagerismscore = _plagerismscore;
      grade          = _grade;
      type           = _type;
      defensedate    = _defensedate;
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
      capstoneid = _capstoneid;
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
   
   public ArrayList<ArrayList<String>> fetch(String _capstoneid){
      ArrayList<String> item = new ArrayList<String>();
      item.add(_capstoneid);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM capstone WHERE capstoneid = ?;",item);

      if (!fetchData.isEmpty()) {
         capstoneid = fetchData.get(1).get(0);
         title = fetchData.get(1).get(1);
         desc = fetchData.get(1).get(2);
         plagerismscore = fetchData.get(1).get(3);
         grade = fetchData.get(1).get(4);
         type = fetchData.get(1).get(5);
         defensedate = fetchData.get(1).get(6);
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
      item.add(defensedate);
      item.add(""+capstoneid);
      boolean put = capstone_project.setData("UPDATE capstone SET title = ?, desc = ?, plagerismscore = ?, grade = ?, type = ?, defensedate = ? WHERE capstoneid = ?;", item);
      return put;
      
   }
   
   
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
      return capstone_project.setData("INSERT INTO capstone (username, typeid, title, abstract) VALUES (?,?,?,?);", item);

   }
   
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(""+capstoneid);
      boolean delete = capstone_project.setData("DELETE FROM capstone WHERE capstoneid = ?;",item);
      return delete;
   }
   
}