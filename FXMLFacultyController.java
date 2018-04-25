import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;

import javax.xml.soap.Text;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cjcot on 4/14/2018.
 */
public class FXMLFacultyController implements iUserController{
    private String curUser;

    //Brings in the elements from the FXML that we'll be writing to or getting data from
    @FXML
    private TableView<trackTable> facultyTrackTable;
    @FXML
    private TableColumn<trackTable, String> track_col_capstoneid;
    @FXML
    private TableColumn<trackTable, String> track_col_name;
    @FXML
    private TableColumn<trackTable, String> track_col_username;
    @FXML
    private TableColumn<trackTable, String> track_col_abstract;
    @FXML
    private TableColumn<trackTable, String> track_col_pscore;
    @FXML
    private TableColumn<trackTable, String> track_col_grade;


    //implement function in capstone to get capstone title/student name/username/abstract/lastupdate/status/pscore/grade returns arraylist<String>
    @FXML protected void handleTrackLoadButtonAction(ActionEvent event){
        ObservableList<trackTable> trackobList = FXCollections.observableArrayList();
        facultyTrackTable.setItems(trackobList);
        String username = "";
        //Check if the text box has anything in it
        if(trackText.getText() != null && trackText.getText() != ""){
            username = trackText.getText();
        }
        Committee trackComm = new Committee();
        ArrayList<ArrayList<String>> data = trackComm.getTrackedCapstones(curUser);
        for (int x = 0; x < data.size(); x+=2) {//goes through each row
            trackobList.add(new trackTable(data.get(x).get(0), data.get(x).get(1), data.get(x).get(2), data.get(x).get(3), data.get(x).get(4), data.get(x).get(5)));
        }
        //needs implementation still
        //the args for these are the variables set in the associated table (ie trackTable.java has four variables to set)
        track_col_capstoneid.setCellValueFactory(new PropertyValueFactory<>("capname"));
        track_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        track_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        track_col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        track_col_pscore.setCellValueFactory(new PropertyValueFactory<>("pscore"));
        track_col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        facultyTrackTable.setItems(trackobList);
    }



    //Accepted table load button

    @FXML
    private TableView<acceptedTable> facultyAcceptedTable;
    @FXML
    private TableColumn<acceptedTable, String> accepted_col_capstone;
    @FXML
    private TableColumn<acceptedTable, String> accepted_col_name;
    @FXML
    private TableColumn<acceptedTable, String> accepted_col_username;
    @FXML
    private TableColumn<acceptedTable, String> accepted_col_abstract;
    @FXML
    private TableColumn<acceptedTable, String> accepted_col_status;
    @FXML
    private TableColumn<acceptedTable, String> accepted_col_grade;


    @FXML protected void handleAcceptedLoadButtonAction(ActionEvent event){
        ObservableList<acceptedTable> acceptedobList = FXCollections.observableArrayList();
        facultyAcceptedTable.setItems(acceptedobList);
        Committee invComm = new Committee();
        ArrayList<ArrayList<String>> data = invComm.getAcceptedCapstones(curUser);
        for (int x = 0; x < data.size(); x+=2) {//goes through each row
            acceptedobList.add(new acceptedTable(data.get(x).get(0), data.get(x).get(1), data.get(x).get(2), data.get(x).get(3), data.get(x).get(1), data.get(x).get(4)));
        }
        accepted_col_capstone.setCellValueFactory(new PropertyValueFactory<>("title"));
        accepted_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        accepted_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        accepted_col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        accepted_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        accepted_col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        facultyAcceptedTable.setItems(acceptedobList);
    }



    //use accepted table again just because its the same columns
    @FXML
    private TableView<acceptedTable> facultyInvitedTable;
    @FXML
    private TableColumn<acceptedTable, String> invited_col_capstone;
    @FXML
    private TableColumn<acceptedTable, String> invited_col_name;
    @FXML
    private TableColumn<acceptedTable, String> invited_col_username;
    @FXML
    private TableColumn<acceptedTable, String> invited_col_abstract;
    @FXML
    private TableColumn<acceptedTable, String> invited_col_status;


    @FXML protected void handleInvitedLoadButtonAction(ActionEvent event) {
        ObservableList<acceptedTable> invitedobList = FXCollections.observableArrayList();
        facultyInvitedTable.setItems(invitedobList);
        Committee invComm = new Committee();
        ArrayList<ArrayList<String>> data = invComm.getInvitedCapstones(curUser);
        for (int x = 0; x < data.size(); x+=2) {//goes through each row
            invitedobList.add(new acceptedTable(data.get(x).get(0), data.get(x).get(1), data.get(x).get(2), data.get(x).get(3), data.get(x).get(4), data.get(x).get(4)));
        }
        invited_col_capstone.setCellValueFactory(new PropertyValueFactory<>("title"));
        invited_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        invited_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        invited_col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        invited_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        facultyInvitedTable.setItems(invitedobList);
    }

    @FXML
    private TableView<FacultyHistoryTable> historyTable;
    @FXML
    private TableColumn<FacultyHistoryTable, String> history_col_date;
    @FXML
    private TableColumn<FacultyHistoryTable, String> history_col_username;
    @FXML
    private TableColumn<FacultyHistoryTable, String> history_col_title;
    @FXML
    private TableColumn<FacultyHistoryTable, String> history_col_status;
    @FXML
    private TableColumn<FacultyHistoryTable, String> history_col_desc;
    @FXML
    private TextField historyText;



    @FXML protected void handleHistoryLoadButtonAction(ActionEvent event){
        ObservableList<FacultyHistoryTable> historyobList = FXCollections.observableArrayList();
        String username = "";
        if(historyText.getText() != null && !historyText.getText().equals("")){
            username = historyText.getText();
        }
        historyTable.setItems(historyobList);
        Capstone getid = new Capstone();
        String capid = getid.getCapstoneId(username);
        StatusHistory hist = new StatusHistory();
        ArrayList<ArrayList<String>> data = hist.getCapstoneHistory(capid);
        for(int x = 0; x < data.size(); x+=2){
            historyobList.add(new FacultyHistoryTable(data.get(x).get(0), data.get(x).get(1), data.get(x).get(2), data.get(x).get(3), data.get(x).get(4)));
        }
        history_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        history_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        history_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        history_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        history_col_desc.setCellValueFactory(new PropertyValueFactory<>("desc"));

        historyTable.setItems(historyobList);

    }



    //Tracking tab and view tab track button
    @FXML
    TextField trackText;
    @FXML
    private TextField viewText;

    @FXML protected void handleTrackTrackButtonAction(ActionEvent event){
        String username = "";
        if(trackText.getText() != null && !trackText.getText().equals("")){
            username = trackText.getText();
        }
        //needs if statement to check if they are invited to committee, if they are, edit that record instead of creating one
        //if they're not invited to the committee
        String capid = "";
        Capstone toTrack = new Capstone();
        capid = toTrack.getCapstoneId(username);
        Committee trackCom = new Committee(curUser, capid, "0", "0", "4", "1");
        trackCom.post();
    }

    @FXML protected void handleViewTrackButtonAction(ActionEvent event){
        String username = "";
        if(viewText.getText() != null && !viewText.getText().equals("")){
            username = viewText.getText();
        }
        //needs if statement to check if they are invited to committee, if they are, edit that record instead of creating one
        //if they're not invited to the committee
        String capid = "";
        Capstone toTrack = new Capstone();
        capid = toTrack.getCapstoneId(username);
        Committee trackCom = new Committee(curUser, capid, "0", "0", "4", "1");
        trackCom.post();
    }

    @FXML
    TextField accepted_grade_box;
    @FXML
    TextField accepted_username_box;

    @FXML protected void handleAcceptedGradeButtonAction(ActionEvent event){
        String username = "";
        String grade = "";
        if(accepted_grade_box.getText() != null && !accepted_grade_box.getText().equals("")){
            grade = accepted_grade_box.getText();
        }
        if(accepted_username_box.getText() != null && !accepted_username_box.getText().equals("")){
            username = accepted_username_box.getText();
        }
        //get capstone item, set grade then post
        Capstone toGrade = new Capstone();
        String capid = toGrade.getCapstoneId(username);
        toGrade.fetch(capid);
        toGrade.setGrade(grade);
        toGrade.put();
    }

    @FXML
    TextField invited_text_area;//user inputs username of capstone they want to accept/reject
     // Takes in the username given in invited_text_area and accepts the capstone for this.
    @FXML protected void handleInvitedAcceptButtonAction(ActionEvent event){
        String username = "";
        if(invited_text_area.getText() != null && invited_text_area.getText() != ""){
            username = invited_text_area.getText();
        }
        //get committee item, set accepted to 1 then post
        Capstone getid = new Capstone();//just to grab the capid
        String capid = getid.getCapstoneId(username);
        Committee toAccept = new Committee();
        toAccept.fetch(capid, curUser);
        toAccept.setAccepted("1");//accepts the invite
        toAccept.put();
    }
    //Takes in the username given from the invited_text_area and rejects the capstone for this.
    @FXML protected void handleInvitedRejectButtonAction(ActionEvent event){
        String username = "";
        if(invited_text_area.getText() != null && !invited_text_area.getText().equals("")){
            username = invited_text_area.getText();
        }
        //get committee item, set declined to 1 then post
        Capstone getid = new Capstone();
        String capid = getid.getCapstoneId(username);
        Committee toReject = new Committee();
        toReject.fetch(capid, curUser);
        toReject.setDeclined("1");
        toReject.put();
    }


    //view tab info
    @FXML
    private TableView<viewTable> facultyViewTable;
    @FXML
    private TableColumn<viewTable, String>  col_capstoneid;
    @FXML
    private TableColumn<viewTable, String> col_studentName;
    @FXML
    private TableColumn<viewTable, String>  col_username;
    @FXML
    private TableColumn<viewTable, String> col_abstract;
    @FXML
    private TableColumn<viewTable, String>  col_pscore;
    @FXML
    private TableColumn<viewTable, String>  col_grade;


    @FXML protected void handleViewViewButtonAction(){
        ObservableList<viewTable> viewobList = FXCollections.observableArrayList();
        facultyViewTable.setItems(viewobList);
        String capid = "";
        if(viewText.getText() != null && !viewText.getText().equals("")){
            capid = viewText.getText();
        }
        Capstone viewCap = new Capstone();
        ArrayList<ArrayList<String>> capToView = viewCap.getView(capid);
        viewobList.add(new viewTable(capToView.get(0).get(0), capToView.get(0).get(1), capToView.get(0).get(2), capToView.get(0).get(3), capToView.get(0).get(4), capToView.get(0).get(5)));
        col_capstoneid.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        col_pscore.setCellValueFactory(new PropertyValueFactory<>("pscore"));
        col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        facultyViewTable.setItems(viewobList);
    }


    @Override
    public void setUsername(String username) {
        curUser = username;
    }
}
