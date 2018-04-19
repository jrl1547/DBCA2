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
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.*;
import javafx.fxml.FXMLLoader;

public class FXMLLoginController {

   @FXML private Button login;
   @FXML private TextField username;
   @FXML private PasswordField password;
   @FXML private Text loginstatus;
 
   @FXML protected void checklogin(ActionEvent ae) {
      Users userlogin = new Users(username.getText());
      if( userlogin.login( username.getText(), password.getText() ) ){
         String loader = null;
         String role = userlogin.getRole( username.getText() );
         Parent root;
         System.out.println(role);
         
         if( role.equals("student")){
            loader = "Student_Homepage.fxml";
         }
         else if( role.equals("faculty")){
            loader = "Faculty_Homepage.fxml";
         }
         else if( role.equals("staff")){
            loader = "Staff_Homepage.fxml";
         }
         else if( role.equals("super")){
            loader = "Admin_Login.fxml";
         }
         else if( role.equals("admin")){
            loader = "Admin_Login.fxml"; 
         }
         else{
            loginstatus.setText("FAIL.");
            return;
         }
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(loader));
            root = fxmlLoader.load();
            iUserController controller = fxmlLoader.getController();
            controller.setUsername(userlogin.getUsername());

            Stage stage = new Stage();
            stage.setTitle("RIT Capstone: " + loader);
            stage.setScene(new Scene(root, 1280, 800));
            stage.show();
            ((Node)(ae.getSource())).getScene().getWindow().hide();
         }
         catch(Exception ioe){
            ioe.printStackTrace();
         }         
         
      }
      else{
         loginstatus.setText("FAIL.");
      }
   } 

}