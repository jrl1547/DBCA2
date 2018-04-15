import java.util.*;

public class Capstone{


   String capstoneid;
   String title;
   String desc;
   String plagerismscore;
   String grade;
   String type;
   String defensedate;
   Database capstone_project;


   
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
   public Capstone(String title, String description, String defensedate){
      this.title = title;
      this.desc = description;
      this.defensedate = defensedate;
      //postInit();
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
      fetchData.remove(0);
      
      capstoneid      = fetchData.get(0).get(0).toString();
      title           = fetchData.get(0).get(1).toString();
      desc            = fetchData.get(0).get(2).toString();
      plagerismscore  = fetchData.get(0).get(3).toString();
      grade           = fetchData.get(0).get(4).toString();
      type            = fetchData.get(0).get(5).toString();
      defensedate     = fetchData.get(0).get(6).toString();
      
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
      boolean post = capstone_project.setData("INSERT INTO `capstone_project`.`capstone` (`capstoneid`, `title`, `desc`, `plagerismscore`, `grade`, `type`, `defensedate`) VALUES (?,?,?,?,?,?,?);", item);
      return post;
   }

   public boolean postInit(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(title);
      item.add(desc);
      item.add(defensedate);
      boolean post = capstone_project.setData("INSERT INTO `capstone_project`.`capstone` (`title`, `desc`, `defensedate`) VALUES (?,?,?);", item);
      return post;
   }
   
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(""+capstoneid);
      boolean delete = capstone_project.setData("DELETE FROM capstone WHERE capstoneid = ?;",item);
      return delete;
   }
   
}