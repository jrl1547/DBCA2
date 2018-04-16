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

//Always need to extend app
public class LoginPage extends Application{
    //Always needs to be below format
    @Override
   public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
 
      Scene scene = new Scene(root);
      stage.setTitle("RIT Capstone Login");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]){
               
      launch(args);      
   }
   //https://stackoverflow.com/questions/33881046/how-to-connect-fx-controller-with-main-app
   //https://docs.oracle.com/javase/8/javafx/get-started-tutorial/fxml_tutorial.html
}
