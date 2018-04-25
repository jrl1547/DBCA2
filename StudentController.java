import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class StudentController implements Initializable, iUserController{
    private StudentDetails student;
    private Users studentUser;
    private Committee committee;
    private Capstone capstone;
    private ProjectTypes types = new ProjectTypes();

    private ArrayList<ArrayList<String>> typesLookup = new ArrayList<>();

    @FXML private TextField newCapTitle, newCapChair, newCapReader1, newCapReader2;
    @FXML private TextArea newCapAbstract, capInfoTextArea, adminUserInfoTextArea;
    @FXML private DatePicker newCapDefenseDate;
    @FXML private Text updateInfo1, updateInfo2;
    @FXML private ComboBox<String> newCapType;
    @FXML private Label newCapErrors;


    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                        if (info != null) {    //not printing ids or null info
                            output += title.substring(0, 1).toUpperCase() + title.substring(1) +
                                    ": " + info + "\n";
                        }
                    }
                } else {    //give no info
                    output = "No available capstone";
                }
            } else {    //give no info
                output = "No available capstone";
            }

            capInfoTextArea.setText(output);    //set text
        }
    }

    @FXML
    /**
     * View 2 most recent updates made to capstone if any
     */
    public void viewUpdates(){
        String output = ""; //output to build on
        if (student != null) {  //check that student has be set
            String capId = student.getCapstoneId(); //get students capstone if
            if (capId != null) {    //check that capId is not null
                StatusHistory history = new StatusHistory(); //initialize status history
                Status status = new Status();
                ArrayList<ArrayList<String>> historyInfo = history.fetch(capId);    //fetch cap info

                if(historyInfo.size() == 1) {   //exactly one status history
                    status.fetch(historyInfo.get(0).get(0));
                    updateInfo1.setText(status.getName());

                    updateInfo2.setText("");
                } else  if (historyInfo.size() >= 2){   //at least 2 status history
                    status.fetch(historyInfo.get(0).get(0));
                    updateInfo1.setText(status.getName());

                    status.fetch(historyInfo.get(1).get(0));
                    updateInfo2.setText(status.getName());
                }else {   //no status history
                    updateInfo1.setText("");
                    updateInfo2.setText("");
                }
            } else {    //give no info
                updateInfo1.setText("");
                updateInfo2.setText("");
            }
        }
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

                    if (info != null) {    //not printing ids or null info
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
    public void loadCapstone() {
       //get types as a look up list and add them to combobox
        typesLookup = types.getTypes();
        ArrayList<String> temp = new ArrayList<>();
        for ( ArrayList<String> arr: typesLookup) {
            temp.add(arr.get(1));
        }
        ObservableList<String> typeList = FXCollections.observableArrayList(temp);
        newCapType.setItems(typeList);

        if(capstone != null){
            //newCapType.types.getTypeName(capstone.getType());
            newCapTitle.setText(capstone.getTitle());
            newCapAbstract.setText(capstone.getDesc());
            if(capstone.getDefensedate() != null) {
                String[] dd = capstone.getDefensedate().split("-");
                newCapDefenseDate.setValue(LocalDate.of(Integer.parseInt(dd[0]), Integer.parseInt(dd[1]),
                        Integer.parseInt(dd[02])));
            }
            newCapType.setValue(types.getTypeName(capstone.getType()));
        }

        if(committee != null){
            //chair
            ArrayList<ArrayList<String>> users = committee.fetchRoles("2");
            if (!users.isEmpty()) {
                newCapChair.setText(users.get(0).get(0));
            }
            users = committee.fetchRoles("3");
            if (!users.isEmpty()){
                if (users.size() == 1){
                    newCapReader1.setText(users.get(0).get(0));
                } else {
                    newCapReader1.setText(users.get(0).get(0));
                    newCapReader2.setText(users.get(1).get(0));
                }
            }
        }
    }

    /**
     * Go through typesLookup and look for status, return id for that status
     *      if it doesn't exist return null
     * @param type    status to get id of
     * @return      returns status id
     */
    private String getTypeId(String type){
        for ( ArrayList<String> arr: typesLookup) {
            if (arr.get(1).equals(type)){
                return arr.get(0);
            }
        }
        return null;
    }

    @FXML
    protected  void handleCapstoneSubmit(ActionEvent actionEvent){
        //Validate form
        String output = ""; //output to put out for user
        if (newCapTitle.getText().equals("")){  //check for title
            output += "Missing title, ";
        }
        if (newCapAbstract.getText().equals("")){   //check for description
            output += "Missing description, ";
        }
        if (newCapDefenseDate.getValue() == null){  //check for defense date
            output += "Missing defense date, ";
        }
        Users tempUser = new Users();   //temp user to check if chairs and readers exist
        if (newCapType.getValue() == null){ //check type, user to check for 1 or both readers
            output += "Missing type, ";
            if (newCapReader1.getText().equals("")){    //check that first reader has been set
                output += "Missing a first reader, ";
            } else if (tempUser.fetch(newCapReader1.getText()).size() != 1){ //check that first reader exists
                output += "First reader does not exist, ";
            }
        } else {
            if (newCapReader1.getText().equals("")){    //check that first reader has been set
                output += "Missing a first reader, ";
            } else if (tempUser.fetch(newCapReader1.getText()).size() != 1){ //check that first reader exists
                output += "First reader does not exist, ";
            }
            if (newCapType.getValue().equals("project")) {   //if project check for second reader
                if (newCapReader2.getText().equals("")) {    //check if second reader set
                    output += "Missing a second reader, ";
                } else if (tempUser.fetch(newCapReader2.getText()).size() != 1) { //check if second reader exists
                    output += "Second reader does not exist, ";
                }
            }
        }
        if (newCapChair.getText().equals("")){  //check that chair is set
            output += "Missing a chair, ";
        } else if (tempUser.fetch(newCapChair.getText()).size() != 1){   //check that chair exists
            output += "Chair does not exist, ";
        }

        if (output.length() > 0) {  //if there are errors, escape
            newCapErrors.setText(output);
            return;
        }

        if (capstone == null || capstone.getCapstoneID() == null){  //create new capstone and new committee
            submitNewCapstone();
            submitNewCommittee();
        } else {    //update capstone
            updateCapstone();
            updateCommittee();
        }
    }

    //-------------------- Perform Submissions ------------------------//
    /**
     * Get information for new capstone and add it to the database
     */
    protected void submitNewCapstone() {

        //Get information from from inputs
        String newTitle = newCapTitle.getText(),
                newDesc = newCapAbstract.getText(),
                newType = getTypeId(newCapType.getValue());

        LocalDate ld = newCapDefenseDate.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
        Date newDate = c.getTime();

        System.out.println("Title: " + newTitle + ", Desc: " + newDesc + ", Date: " + newDate.toString());

        //Give some sort of feed back before returning
        student.setCapstonestart(LocalDate.now().toString());
        capstone =  new Capstone(newTitle, student.getUsername(), newType, newDesc, newDate.toString());
            String capId = capstone.getCapstoneID();
    }

    /**
     * Submit all necessary members, 1 reader and chair for all capstones
     *      and a second reader for projects
     */
    protected  void submitNewCommittee(){
        submitNewCommitteeMember(newCapChair.getText(), "2");
        submitNewCommitteeMember(newCapReader1.getText(), "3");
        if(newCapType.getValue().equals("project")){    //check if project before creating second user
            submitNewCommitteeMember(newCapReader2.getText(), "3");
        }
    }

    /**
     * create new Committee Member
     */
    protected void submitNewCommitteeMember(String username, String role) {
        Committee tempComm = new Committee();
        tempComm.setAccepted("0");
        tempComm.setDeclined("0");
        tempComm.setTracking("0");
        tempComm.setCapstoneID(capstone.getCapstoneID());
        tempComm.setUsername(username);
        tempComm.setPosition(role);
        tempComm.post();
    }

    //-------------------- Perform Updates ------------------------//
    /**
     * Insert all inputs into capstone and update capstone using a put
     */
    protected void updateCapstone() {
        //Get information from from inputs
        String newTitle = newCapTitle.getText(),
                newDesc = newCapAbstract.getText(),
                newType = getTypeId(newCapType.getValue());

        LocalDate ld = newCapDefenseDate.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
        Date newDate = c.getTime();

        capstone.setTitle(newTitle);
        capstone.setDesc(newDesc);
        capstone.setType(newType);
        capstone.setDefensedate(newDate.toString());
        capstone.put();
    }

    /**
     * Update chair and reader for the students committee as needed
     */
    protected void updateCommittee(){
        Committee tempComm = new Committee();   //create temp committee to work with
            tempComm.setCapstoneID(student.getCapstoneId());

        ArrayList<ArrayList<String>> chair = tempComm.fetchRoles("2");  //fetch chair
        if(chair.isEmpty() || !chair.get(0).get(0).equals(newCapChair.getText())) {    //if this user is not already a chair
            submitNewCommitteeMember(newCapChair.getText(), "2");
        }

        ArrayList<ArrayList<String>> readers = tempComm.fetchRoles("3"); //fetch all available readers that have not declined
        if(newCapType.getValue().equals("project")){
            if(readers.isEmpty()){    //there are no readers that have not declined, submit both new readers
                submitNewCommitteeMember(newCapReader1.getText(), "3");
                submitNewCommitteeMember(newCapReader2.getText(), "3");

            } else if (readers.size() == 1){    //there is only 1 reader that has not declined
                if (!readers.get(0).get(0).equals(newCapReader1.getText())) {
                    //check that this reader has not already be submitted
                    submitNewCommitteeMember(newCapReader1.getText(), "3");
                }
                if (!readers.get(0).get(0).equals(newCapReader2.getText())) {
                    //check that this reader has not already be submitted
                    submitNewCommitteeMember(newCapReader2.getText(), "3");
                }

            } else {    //there are 2 current readers that have not declined
                if (!readers.get(0).get(0).equals(newCapReader1.getText()) &&
                        !readers.get(1).get(0).equals(newCapReader1.getText())) {
                    //check that this reader has not already be submitted
                    submitNewCommitteeMember(newCapReader1.getText(), "3");
                }
                if (readers.size() > 1 && !readers.get(0).get(0).equals(newCapReader2.getText()) &&
                        !readers.get(1).get(0).equals(newCapReader2.getText())) {
                    //check that this reader has not already be submitted
                    submitNewCommitteeMember(newCapReader2.getText(), "3");
                }
            }
        } else {
            if(readers.isEmpty()){    //there are no readers that have not declined, submit new readers
                submitNewCommitteeMember(newCapReader1.getText(), "3");

            } else {    //there is  1 reader that has not declined
                if (!readers.get(0).get(0).equals(newCapReader1.getText())) {
                    //check that this reader has not already be submitted
                    submitNewCommitteeMember(newCapReader1.getText(), "3");
                }
            }
        }
    }

    @Override
    public void setUsername(String username) {
        this.student = new StudentDetails(username);
            student.fetch();
        this.capstone = new Capstone();
        ArrayList<ArrayList<String>> data = capstone.fetch(student.getCapstoneId());
             if (capstone.fetch(student.getCapstoneId()).size() < 2){
                 capstone = null;
             }
        this.committee = new Committee();
            if (committee.fetch(student.getCapstoneId()).size() == 0){
                committee = null;
            }
        System.out.println(student.getUsername());
        loadProject();
    }
}