import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable{

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProject();
    }

    @FXML
    public void loadProject() {

    }

    @FXML
    public void loadAdmin() {

    }

    @FXML
    public void loadNewCapstone() {

    }

    @FXML
    protected Capstone submitNewCapstone(ActionEvent actionEvent) {
        //Get information from from inputs and create it
        String newTitle = "",
                newDesc = "",
                newDD = "";


        return new Capstone("title", "description", "defense date");
    }

}