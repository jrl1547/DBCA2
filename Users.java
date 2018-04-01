import java.util.*;


/**
*  @author     Jason Lamb
*  DBCA - Database
*
*  The purpose of this class is to do the basic functionality of a Database - connect, close, query, and manipulate the database.
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

   public Users(){
   
   
   }
   
   public Users(String _username){
   
      username    = _username;
      capstone_project = new Database();
   
   }
   
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
   
   private String getUsername(){
      return username;
   }
   
   private String getRole(){
      return role;
   }
   
   private String getPassword(){
      return password;
   }
   
   private String getFullname(){
      return fullname;
   }
   
   private String getEmail(){
      return email;
   }
   
   private String getPhone(){
      return phone;
   }
   
   private String getDepartment(){
      return department;
   }
   
   private void setUsername(String _username){
      username = _username;
   }
   
   private void setRole(String _role){
      role = _role;
   }
   
   private void setPassword(String _password){
      password = _password;
   }
   
   private void setFullname(String _fullname){
      fullname = _fullname;
   }
   
   private void setEmail(String _email){
      email = _email;
   }
   
   private void setPhone(String _phone){
      phone = _phone;
   }
   
   private void setDepartment(String _department){
      department = _department;
   }


   public ArrayList fetch(String username){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM users WHERE username = ?;",item);             
      return fetchData;
   }
   
   public void fetchAndSet(String username){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      ArrayList<ArrayList<String>> fetchData = capstone_project.getData("SELECT * FROM users WHERE username = ?;",item);
      fetchData.remove(0);             
      
   }
   
   public boolean put(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(role);
      item.add(password);
      item.add(fullname);
      item.add(email);
      item.add(phone);
      item.add(department);
      boolean put = capstone_project.setData("UPDATE users SET role = ?, password = ?, fullname = ?, email = ?, phone = ?, department = ? WHERE username = ?;", item);
      return put;
   }
   
   public boolean post(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      item.add(role);
      item.add(password);
      item.add(fullname);
      item.add(email);
      item.add(phone);
      item.add(department);
      boolean post = capstone_project.setData("INSERT INTO users (`username`, `role`, `password`, `fullname`, `email`, `phone`, `department`) VALUES ('?','?','?','?','?','?','?');", item);
      return post;
   }
   
   public boolean delete(){
      ArrayList<String> item = new ArrayList<String>();
      item.add(username);
      boolean delete = capstone_project.setData("DELETE FROM equipment WHERE username = '?';",item);
      return delete;
   }
}