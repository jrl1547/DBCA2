import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class StudentController implements Initializable{
    private StudentDetails student;
    @FXML private TextField newCapTitle;
    @FXML private TextArea newCapAbstract,
            capInfoTextArea;
    @FXML private DatePicker newCapDefenseDate;
    @FXML private Text updateDate1,
            updateInfo1,
            updateDate2,
            updateInfo2;


    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProject();
    }

    public void setStudent(StudentDetails student){
        this.student = student;
        System.out.println(student.getUsername());
        loadProject();
    }

    @FXML
    public void loadProject() {
        //TODO load current project
        if (student != null) {
            String capId = student.getCapstoneId(),
                    output = "";
            if (capId != null) {
                Capstone cap = new Capstone(capId);
                cap.fetch(capId);
                output += "Title: " + cap.getTitle() + "\n Description: " + cap.getDesc();
                capInfoTextArea.setText(output);
            } else {
                capInfoTextArea.setText("No Current Capstone");
            }
        }
    }

    @FXML
    public void viewUpdates(){
        //TODO view the 2 most recent updates made to project
    }

    @FXML
    public void editCapstone(){
        //TODO open/build capstone edit page
    }

    @FXML
    public void loadAdmin() {
        //not really loading anything for this either, just updating info
    }

    @FXML
    public void loadNewCapstone() {
        //does anything need to be loaded       -- faculty?? or should that be a validation check
        //                                              don't necessarily need it until submission
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
        student.setCapstonestart("true");
        return new Capstone(newTitle, newDesc, newDate.toString());
    }

}