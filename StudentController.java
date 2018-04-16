import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class StudentController implements Initializable{
    private StudentDetails student;
    private Capstone capstone;
    private Database db = new Database();
    private ArrayList<ArrayList<String>> typesLookup = new ArrayList<>();
    @FXML private TextField newCapTitle;
    @FXML private TextArea newCapAbstract,
            capInfoTextArea,
            adminUserInfoTextArea;
    @FXML private DatePicker newCapDefenseDate;
    @FXML private Text updateDate1,
            updateInfo1,
            updateDate2,
            updateInfo2;
    @FXML private ComboBox<String> newCapType;


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
    /**
     * Get and print out all available information on the student's current capstone if one is available
     */
    public void loadProject() {
        String output = ""; //output to build on
        if (student != null) {  //check that student has be set
            String capId = student.getCapstoneId(); //get students capstone if
            if (capId != null) {    //check that capId is not null
                capstone = new Capstone(capId); //initialize capstone
                ArrayList<ArrayList<String>> capInfo = capstone.fetch(capId);    //fetch cap info
                if(capInfo.size() == 2) {   //check that we have 2 rows, titles and info
                    //loop through and print each title and its corresponing info
                    for (int i = 0, len = capInfo.get(0).size(); i < len; i++) {
                        String title = capInfo.get(0).get(i),
                                info = capInfo.get(1).get(i);

                        String lower = title.toLowerCase();
                        if (info != null && !lower.contains("id")) {    //not printing ids or null info
                            output += title.substring(0, 1).toUpperCase() + title.substring(1) +
                                    ": " + info + "\n";
                        }
                    }
                } else {    //give no info
                    output = "No available capstone";
                }
            } else {    //give no info
                output = "No Current Capstone";
            }

            capInfoTextArea.setText(output);    //set text
        }
    }

    @FXML
    /**
     * View 2 most recent updates made to capstone if any
     */
    public void viewUpdates(){
        //TODO view the 2 most recent updates made to project
        String output = ""; //output to build on
        if (student != null) {  //check that student has be set
            String capId = student.getCapstoneId(); //get students capstone if
            if (capId != null) {    //check that capId is not null
                StatusHistory history = new StatusHistory(); //initialize status history
                Status status = new Status();
                ArrayList<ArrayList<String>> historyInfo = history.fetch(capId);    //fetch cap info

                if(historyInfo.size() == 1) {   //check that we have 2 rows, titles and info
                    //loop through and print each title and its corresponing info
                    updateDate1.setText(historyInfo.get(0).get(2));
                    status.fetch(historyInfo.get(0).get(0));
                    updateInfo1.setText(status.getDescription());

                    updateDate2.setText("No available update");
                    updateInfo2.setText("");
                } else  if (historyInfo.size() >= 2){
                    updateDate1.setText(historyInfo.get(0).get(2));
                    status.fetch(historyInfo.get(0).get(0));
                    updateInfo1.setText(status.getDescription());

                    updateDate2.setText(historyInfo.get(1).get(2));
                    status.fetch(historyInfo.get(1).get(0));
                    updateInfo2.setText(status.getDescription());
                }else {    //give no info
                    updateDate1.setText("No available update");
                    updateInfo1.setText("");
                    updateDate2.setText("No available update");
                    updateInfo2.setText("");
                }
            } else {    //give no info
                updateDate1.setText("No available update");
                updateInfo1.setText("");
                updateDate2.setText("No available update");
                updateInfo2.setText("");
            }
        }
    }

    @FXML
    /**
     * Make changes to capstone
     */
    public void editCapstone(){
        //TODO open/build capstone edit page
    }

    @FXML
    /**
     * Load admin information, all available info about the student
     */
    public void loadAdmin() {
        String output = ""; //output to build on
        if (student != null) {  //check that student has been set
            ArrayList<ArrayList<String>> studentInfo = student.fetch(); //fetch all data
            if(studentInfo.size() == 2) {   //make sure we have title row and info row
                //then go through and print all title and their info
                for (int i = 0, len = studentInfo.get(0).size(); i < len; i++) {
                    String title = studentInfo.get(0).get(i),
                            info = studentInfo.get(1).get(i);

                    String lower = title.toLowerCase();
                    if (info != null && !lower.contains("id")) {    //not printing ids or null info
                        output += title.substring(0, 1).toUpperCase() + title.substring(1) +
                                ": " + info + "\n";
                    }
                }
            } else{ //give no info
                output = "No information available";
            }
        } else {    //give no info
            output = "No information available";
        }

        adminUserInfoTextArea.setText(output);  //set text
    }

    @FXML
    public void loadNewCapstone() {
       //get types as a look up list and add them to combobox
        getTypeLookup();
        ArrayList<String> temp = new ArrayList<>();
        for ( ArrayList<String> arr: typesLookup) {
            temp.add(arr.get(1));
        }
        ObservableList<String> typeList = FXCollections.observableArrayList(temp);
        newCapType.setItems(typeList);
    }

    /**
     * Get types and append them to a lookup 2d array
     */
    public void getTypeLookup(){
        String query = "SELECT * FROM types";
        typesLookup = db.getData(query);
    }

    /**
     * Go through typesLookup and look for status, return id for that status
     *      if it doesn't exist return null
     * @param status    status to get id of
     * @return      returns status id
     */
    public String getStatusId(String status){
        for ( ArrayList<String> arr: typesLookup) {
            if (arr.get(1).equals(status)){
                return arr.get(0);
            }
        }
        return null;
    }

    @FXML
    /**
     * Get information for new capstone and add it to the database
     */
    protected void submitNewCapstone(ActionEvent actionEvent) {

        //Get information from from inputs
        String newTitle = newCapTitle.getText(),
                newDesc = newCapAbstract.getText(),
                newType = getStatusId(newCapType.getValue());

        //check for missing data
        if (newTitle.equals("") || newDesc.equals("") || newCapDefenseDate.getValue() == null || newCapType.getValue() == null){
            //give feedback that data is missing
            return;
        }

        LocalDate ld = newCapDefenseDate.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
        Date newDate = c.getTime();


        System.out.println("Title: " + newTitle + ", Desc: " + newDesc + ", Date: " + newDate.toString());

        //Give some sort of feed back before returning
        student.setCapstonestart("true");
        capstone =  new Capstone(newTitle, student.getUsername(), newType, newDesc, newDate.toString());
        student.setCapstonestart("true");
    }

}