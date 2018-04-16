import java.util.*;


/**
*  @author     Jason Lamb
*  DBCA - Database
*
*  The purpose of this class is to CRUD the users table. 
*
*
*/

public class Users{

   private String username;
   private String role;
   private String password;
   private String fullname;
   private String email;
   private String phone;
   private String department;
   private Database capstone_project;

   /**********************************************************************************
   *                                   CONSTRUCTORS                                  *
   **********************************************************************************/

   /*
   *  Default Constructor
   */
   public Users(){
      capstone_project = new Database();  
   }
   
   /**
   *  This constructor takes in a username, and starts an instance of the Database. 
   */
   
   public Users(String _username){
   
      username    = _username;
      capstone_project = new Database();
   
   }
   
   
   /**
   * This constructor takes all arguments for a user. Can be useful for creating a new user. 
   */
   public Users(String _username, String _role, String _password, String _fullname, String _email, String _phone, String _department){
   
      username    = _username;
      role        = _role;
      password    = _password;
      fullname    = _fullname;
      email       = _email;
      phone       = _phone;
      department  = _department;
      capstone_project = new Database();
         
   }
   
   
   /**********************************************************************************
   *                                   ACCESSORS                                     *
   **********************************************************************************/
   /**
   * This method returns the username
   *  @return username
   */
   String getUsername(){
      return username;
   }
   
   /**
   * This method returns the role
   *  @return role
   */
   String getRole(){
      return role;
   }
   
   /**
   * This method returns the password
   *  @return password
   */   
   String getPassword(){
      return password;
   }
   
   /**
   * This method returns the full name
   *  @return fullname
   */   
   String getFullname(){
      return fullname;
   }
   
   /**
   * This method returns the email
   *  @return email
   */
   String getEmail(){
      return email;
   }
   
   /**
   * This method returns the phone number
   *  @return phone
   */
   String getPhone(){
      return phone;
   }
   
   /**
   * This method returns the department
   *  @return department
   */
   String getDepartment(){
      return department;
   }
   
   /**********************************************************************************
   *                                   MUTATORS                                      *
   **********************************************************************************/
   
   /**
   * This method sets the username
   *  @return username
   */ 
   void setUsername(String _username){
      username = _username;
   }
   
   /**
   * This method sets the role
   *  @return role
   */
   void setRole(String _role){
      role = _role;
   }
   
   /**
   * This method sets the password
   *  @return password
   */
   void setPassword(String _password){
      password = _password;
   }
   
   /**
   * This method sets the fullname
   *  @return fullname
   */
   void setFullname(String _fullname){
      fullname = _fullname;
   }
   
   /**
   * This method sets the email
   *  @return email
   */
   void setEmail(String _email){
      email = _email;
   }
   
   /**
   * This method sets the phone
   *  @return phone
   */
   void setPhone(String _phone){
      phone = _phone;
   }
   
   /**
   * This method sets the department
   *  @return department
   */
   void setDepartment(String _department){
      department = _department;
   }

   /**********************************************************************************
   *                                   METHODS                                       *
   **********************************************************************************/


   /**
   *  This method calls getData and sends a String and ArrayList, it  gets back a 2D ArrayList that holds the contents of what was returned. 
   *  it then removes the first Element, which is the column names. It them inserts each value into the appropriate variable.
   *  @return fetchData
   */   
   public ArrayList<ArrayList<String>> fetch(String username){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM users WHERE username = ?;",item);
      fetchData.remove(0);
      username    = fetchData.get(0).get(0).toString();
      role        = fetchData.get(0).get(1).toString();
      password    = fetchData.get(0).get(2).toString();
      fullname    = fetchData.get(0).get(3).toString();
      email       = fetchData.get(0).get(4).toString();
      phone       = fetchData.get(0).get(5).toString();
      department  = fetchData.get(0).get(6).toString();
      
      return fetchData;               
      
   }
   /**
   *  This method calls getData and sends a the username and password somebody is attempting to login with. 
   *  On the return of getData, login checks to see if the arraylist is empty or not. If it is not empty,
   *  it is assumed the login is correct.
   *  @return boolean if login was successful.
   */    
   public boolean login(String username, String password){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      item.add(password);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT username FROM users WHERE username = ? AND password = sha1(?);",item);
      if(!fetchData.isEmpty()){
         return true;
      }
      else{
         return false;     
      }               
   }
   
   public String getRole(String username){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT role FROM users JOIN roles on users.roleid = roles.roleid WHERE username = ?;", item);
      item.remove(0);
      return fetchData.get(1).get(0);
   }
   
   /**
   *  This method takes in a username, then fetches information about them.
   *  After that, it calls fetchData and gets a 2D arraylist.
   *  Instead of handling array on the front end, it slices the 2D array into a regular arraylist and sends it out.
   *  @return ArrayList<String> item that holds capstone information.
   */
   
   public ArrayList<String> getCapstoneInfo(String username){
      ArrayList<String> item = new ArrayList<String>();
      fetch(username);
      item.add(getUsername());
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT users.username, capstone.capstoneid, capstone.title, capstone.desc, type FROM users JOIN committee ON users.username = committee.username JOIN capstone ON committee.capstoneid = capstone.capstoneid WHERE committee.position LIKE student AND users.username = ?;",item);
      item.remove(1);
      item.add(fetchData.get(0).get(0));  //username
      item.add(fetchData.get(0).get(1));  //capstoneid
      item.add(fetchData.get(0).get(2));  //title
      item.add(fetchData.get(0).get(3));  //description (abstract)
      item.add(fetchData.get(0).get(4));  //type (thesis or project)
      
      return item;
   }
   
   /**
   *  This method calls getData and sends a the username and password somebody is attempting to login with. 
   *  On the return of getData, login checks to see if the arraylist is empty or not. If it is not empty,
   *  it is assumed the login is correct.
   *  @return boolean if capstone was successfully created. 
   */
   public boolean createNewCapstone(String _username, String _title, String _abstract, String _type, String... _committee){
      boolean created = false;
      boolean post = false;
      ArrayList<String> committee = new ArrayList<String>(Arrays.asList(_committee));
      ArrayList<String> item = new ArrayList<String>();
      
      fetch(_username);
      item.add(_title);
      item.add(_abstract);
      item.add(_type);
      post = capstone_project.setData("INSERT INTO capstone (`title`, `desc`,`type`) VALUES (?,?,?);", item);
      if(!post){
         return false; 
      }
      
      ArrayList<ArrayList<String>> capid = capstone_project.getData("SELECT capstoneid FROM capstone WHERE title = ? AND desc = ? AND type = ?;",item);
      
      String studentSetup = _username + "-" + "student";
      committee.add(0, studentSetup);
      ArrayList<String> comInfo = new ArrayList<String>();
      for(String user : committee){
         
         String[] split = user.split("-");
         comInfo.add(split[0]);
         comInfo.add(capid.get(0).get(0));
         comInfo.add(split[1]);
         
         
         post = capstone_project.setData("INSERT INTO committee (`username`, `capstoneid`, `position`) VALUES (?,?,?);", comInfo);
         
         if(!post){
            return false;
         }
      }
      
      //if post is still true, then inserts have been successful. 
      if(post){
         created = true; 
      }
      
      return created;
   }
   
   

   /**
   *  This method calls setData and sends a String and ArrayList, it gets back a boolean if the put was successsful or not.
   *  @return boolean if put was successful or not
   */    
   public boolean put(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(role);
      item.add(password);
      item.add(fullname);
      item.add(email);
      item.add(phone);
      item.add(department);
      item.add(username);
      boolean put = capstone_project.setData("UPDATE users SET role = ?, password = sha1(?), fullname = ?, email = ?, phone = ?, department = ? WHERE username = ?;", item);
      return put;
   }
   
   /**
   *  This method calls setData and sends a string to be prepared.
   *  @return boolean if the setData command was successful.
   */
   public boolean post(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      item.add(role);
      item.add(password);
      item.add(fullname);
      item.add(email);
      item.add(phone);
      item.add(department);
      boolean post = capstone_project.setData("INSERT INTO users (`username`, `role`, `password`, `fullname`, `email`, `phone`, `department`) VALUES (?,?,sha1(?),?,?,?,?);", item);
      return post;
   }
   
   /**
   *  This method calls setData and sends a String and ArrayList, it gets back a boolean value if it was successful or not. 
   *  @return boolean true if delete was successful, false if delete did not work. 
   */ 
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      boolean delete = capstone_project.setData("DELETE FROM users WHERE username = ?;",item);
      return delete;
   }
}