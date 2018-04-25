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
public class StudentTester extends Application
{
    //Always needs to be below format
    @Override
    public void start(Stage stage) throws Exception {
        StudentDetails student = new StudentDetails("teststudent");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Faculty_Homepage.fxml"));

        Parent root = fxmlLoader.load();
        StudentController controller = fxmlLoader.getController();
        controller.setUsername("teststudent");

        stage.show();

        Scene scene = new Scene(root);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){

        launch(args);
    }
    //https://stackoverflow.com/questions/33881046/how-to-connect-fx-controller-with-main-app
    //https://docs.oracle.com/javase/8/javafx/get-started-tutorial/fxml_tutorial.html
}
