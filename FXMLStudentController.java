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

public class FXMLStudentController {

   @FXML protected Text pField;
   @FXML protected Text aField;
   @FXML protected Button update;
   @FXML protected Button edit;
 
   @FXML protected void getcapstoneupdates(ActionEvent ae) {
      if(true){
         System.out.println("Capstone Pressed");
         
      }
      else{
      }
   } 
   
   @FXML protected void editcapstone(ActionEvent ae) {
   
      String[] committee = new String[3];
      committee[0] = "gpavks-chair";
      committee[1] = "kssics-reader";
      committee[2] = "sphics-reader";
   
      if(true){
         Users user = new Users("jrl1547");
         user.fetch("jrl1547");
         
         //user.createNewCapstone("jrl1547", "How to not suck.", "A guide on how to not suck.", "Project", committee);
         
         
      }
      else{
      }
   }

}