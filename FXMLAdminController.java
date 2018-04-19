import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import java.util.*;

public class FXMLAdminController {

   ArrayList<String> list = new ArrayList<String>();

   @FXML private Button btn_tabledit, btn_loadtables, btn_download, btn_loadcsv, btn_insertMany, btn_insertOne, btn_delete;
   @FXML private TextField tf_sqlquery, tf_username, tf_fullname, tf_email, tf_phone, tf_dept, tf_csvDir, tf_delUser;
   @FXML private PasswordField tf_pass, tf_confirmpass;
   @FXML private MenuButton mb_role, mnu_listtables;
   @FXML private MenuItem mi_student, mi_staff, mi_faculty, mi_super, mi_admin;
   @FXML private TableView tv_full;
   @FXML private Text txt_result, txt_delete;
   @FXML private TableColumn t1, t2, t3, t4;
   private String roleid;
   
   
   @FXML protected void sellChanged (ActionEvent ae){
      //getRoles();
   
   }
   
   private void getRoles(){
         
      Roles roleList = new Roles();
      ArrayList<ArrayList<String>> roles = roleList.listRoles();
      
      for(ArrayList<String> rol : roles){
         if( rol.toString().length() > 2 ){
             list.add(rol.toString());
         }
      }
   }

   
   @FXML protected void getCSV(ActionEvent ae) {
   
   } 
   
   @FXML protected void ImportCSV(ActionEvent ae) {
   
   } 
   
   @FXML protected void insertManyRecords(ActionEvent ae) {
   
   } 
   
   @FXML protected void DeleteUser(ActionEvent ae) {
      Users user = new Users(tf_delUser.getText());
      if(user.delete()){
         txt_delete.setText(tf_delUser.getText() + " successfully deleted.");
      }
      else{
         txt_delete.setText("Error in deleting user.");
      }
   } 
   
   @FXML protected void EditTable(ActionEvent ae) {
   
   }  
   
   @FXML protected void StudentSwap(ActionEvent ae) {
      mb_role.setText("student");
   }  
   @FXML protected void StaffSwap(ActionEvent ae) {
      mb_role.setText("staff");
   }  
   @FXML protected void FacultySwap(ActionEvent ae) {
      mb_role.setText("faculty");
   }  
   @FXML protected void SuperSwap(ActionEvent ae) {
      mb_role.setText("super");
   }  
   @FXML protected void AdminSwap(ActionEvent ae) {
      mb_role.setText("admin");
   }  
      
   @FXML protected void InsertSingleRecord(ActionEvent ae){
      //System.out.println(tf_username.getText() + " " + mb_role.getText() + " " +  tf_pass.getText() + " " + tf_confirmpass.getText() + " " +  tf_fullname.getText() + " " +  tf_email.getText() + " " +  tf_phone.getText() + " " +  tf_dept.getText());
      try{
         Roles getID = new Roles(mb_role.getText());
         roleid = getID.getRoleId();
      }
      catch(Exception e){
         txt_result.setText("SELECT A ROLE.");      
      }
      
      if( tf_pass.getText().equals(tf_confirmpass.getText()) ){
            
          
         Users user = new Users(tf_username.getText(), roleid, tf_pass.getText(), tf_fullname.getText(), tf_email.getText(), tf_phone.getText(), tf_dept.getText() );
         user.post(); 
         txt_result.setText("USER SUCCESSFULLY ADDED.");    
      }
      else{
         txt_result.setText("PASSWORDS DO NOT MATCH.");
      }
   
   }

}