import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class StudentController implements Initializable{

    @FXML private TextField newCapTitle;
    @FXML private TextArea newCapAbstract;
    @FXML private DatePicker newCapDefenseDate;


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
        String newTitle = newCapTitle.getText(),
                newDesc = newCapAbstract.getText();

        if (newTitle.equals("") || newDesc.equals("") || newCapDefenseDate.getValue() == null){
            //give feedback that data is missing
            return null;
        }

        LocalDate ld = newCapDefenseDate.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
        Date newDate = c.getTime();


        System.out.println("Title: " + newTitle + ", Desc: " + newDesc + ", Date: " + newDate.toString());

        //Give some sort of feed back before returning
        return new Capstone(newTitle, newDesc, newDate.toString());
    }

}