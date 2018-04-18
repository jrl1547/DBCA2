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
public class FXMLFacultyController{
    private Users curUser;

    public void setStudent(Users curUser){
        this.curUser = curUser;
    }
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
    private TableColumn<trackTable, String> track_col_last_update;
    @FXML
    private TableColumn<trackTable, String> track_col_status;
    @FXML
    private TableColumn<trackTable, String> track_col_pscore;
    @FXML
    private TableColumn<trackTable, String> track_col_grade;


    ObservableList<trackTable> trackobList = FXCollections.observableArrayList();
    //implement function in capstone to get capstone title/student name/username/abstract/lastupdate/status/pscore/grade returns arraylist<String>
    @FXML protected void handleTrackLoadButtonAction(ActionEvent event){
        String capid = "";
        //Check if the text box has anything in it
        if(trackText.getText() != null && trackText.getText() != ""){
            capid = trackText.getText();
        }

        //needs implementation still
        //the args for these are the variables set in the associated table (ie trackTable.java has four variables to set)
        track_col_capstoneid.setCellValueFactory(new PropertyValueFactory<>("id"));
        track_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        track_col_name.setCellValueFactory(new PropertyValueFactory<>("username"));
        track_col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        track_col_last_update.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        track_col_name.setCellValueFactory(new PropertyValueFactory<>("status"));
        track_col_name.setCellValueFactory(new PropertyValueFactory<>("pscore"));
        track_col_name.setCellValueFactory(new PropertyValueFactory<>("grade"));

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

    ObservableList<acceptedTable> acceptedobList = FXCollections.observableArrayList();

    @FXML protected void handleAcceptedLoadButtonAction(ActionEvent event){
        Committee invComm = new Committee();
        ArrayList<ArrayList<String>> data = invComm.getAcceptedCapstones(curUser.getUsername());
        for (int x = 0; x < data.size(); x++) {//goes through each row
            acceptedobList.add(new acceptedTable(data.get(x).get(0), data.get(x).get(1), data.get(x).get(2), data.get(x).get(3), data.get(x).get(4), data.get(x).get(5)));
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

    ObservableList<acceptedTable> invitedobList = FXCollections.observableArrayList();

    @FXML protected void handleInvitedLoadButtonAction(ActionEvent event) {
        Committee invComm = new Committee();
        ArrayList<ArrayList<String>> data = invComm.getInvitedCapstones(curUser.getUsername());
        for (int x = 0; x < data.size(); x++) {//goes through each row
            invitedobList.add(new acceptedTable(data.get(x).get(0), data.get(x).get(1), data.get(x).get(2), data.get(x).get(3), data.get(x).get(4), data.get(x).get(5)));
        }
        invited_col_capstone.setCellValueFactory(new PropertyValueFactory<>("title"));
        invited_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        invited_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        invited_col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        invited_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        facultyInvitedTable.setItems(invitedobList);
    }



    //Tracking tab and view tab track button
    @FXML
    TextField trackText;
    @FXML
    private TextField viewText;

    @FXML protected void handleTrackTrackButtonAction(ActionEvent event){
        String capid = "";
        if(trackText.getText() != null && trackText.getText() != ""){
            capid = trackText.getText();
        }
        if(viewText.getText() != null && viewText.getText() != ""){
            capid = viewText.getText();
        }
        //needs if statement to check if they are invited to committee, if they are, edit that record instead of creating one
        //if they're not invited to the committee
        Committee trackCom = new Committee(capid, curUser.getUsername(), "0", "0", "none", "1");
        trackCom.post();
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
    private TableColumn<viewTable, String>  col_last_update;
    @FXML
    private TableColumn<viewTable, String>  col_status;
    @FXML
    private TableColumn<viewTable, String>  col_pscore;
    @FXML
    private TableColumn<viewTable, String>  col_grade;

    //implement function in capstone to get capstone title/student name/username/abstract/lastupdate/status/pscore/grade returns arraylist<String>
    ObservableList<viewTable> viewobList = FXCollections.observableArrayList();

    //should be good
    @FXML protected void handleViewViewButtonAction(){
        String capid = "";
        if(viewText.getText() != null && viewText.getText() != ""){
            capid = viewText.getText();
        }
        Capstone viewCap = new Capstone();
        //ArrayList<String> capToView = viewCap.fetchView(capid);
        //viewobList.add(new viewTable(capToView.get(0), capToView.get(1), capToView.get(2), capToView.get(3), capToView.get(4), capToView.get(5), capToView.get(6), capToView.get(7)));
        col_capstoneid.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        col_last_update.setCellValueFactory(new PropertyValueFactory<>("last_update"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_pscore.setCellValueFactory(new PropertyValueFactory<>("pscore"));
        col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        facultyViewTable.setItems(viewobList);
    }

}
