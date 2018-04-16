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
public class tableController{
    //Brings in the elements from the FXML that we'll be writing to or getting data from
    @FXML
    private TableView<trackTable> facultyTrackTable;
    @FXML
    private TableColumn<trackTable, String> track_col_capstoneid;
    @FXML
    private TableColumn<trackTable, String> track_col_name;
    @FXML
    private TableColumn<trackTable, String> track_col_abstract;
    @FXML
    private TableColumn<trackTable, String> track_col_last_update;


    ObservableList<trackTable> trackobList = FXCollections.observableArrayList();

    @FXML protected void handleTrackLoadButtonAction(ActionEvent event){
        String capid = "";
        //Check if the text box has anything in it
        if(trackText.getText() != null && trackText.getText() != ""){
            capid = trackText.getText();
        }
        try{
            Database dbconn = new Database();
            //String to get data to fill the table on the tracking page
            String select = "SELECT capstone.capstoneid, users.fullname, capstone.abstract, status.description FROM capstone" +
                    "        JOIN users ON capstone.capstoneid = users.capstoneid" +
                    "        JOIN status ON capstone.capstoneid = status.capstoneid" +
                    "        WHERE capstoneid = ?;";
            ArrayList<String> args = new ArrayList<String>();
            args.add(capid);
            ResultSet rs = dbconn.getResultSet(select, args);
            //adds everything to the observable list to be added to the table
            while(rs.next()){
                trackobList.add(new trackTable(rs.getString("capstoneid"), rs.getString("fullname"), rs.getString("abstract"), rs.getString("description")));
            }
            dbconn.close();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        //the args for these are the variables set in the associated table (ie trackTable.java has four variables to set)
        track_col_capstoneid.setCellValueFactory(new PropertyValueFactory<>("id"));
        track_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        track_col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        track_col_last_update.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));

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
    private TableColumn<acceptedTable, String> accepted_col_abstract;

    ObservableList<acceptedTable> acceptedobList = FXCollections.observableArrayList();

    @FXML protected void handleAcceptedLoadButtonAction(ActionEvent event){
        try{
            Database dbconn = new Database();
            String select = "SELECT capstone.title, users.fullname, capstone.abstract FROM capstone" +
                    "        JOIN users ON capstone.capstoneid = users.capstoneid" +
                    "        JOIN committee ON capstone.capstoneid = committee.capstoneid" +
                    "        WHERE has_accepeted = 1";//selects capstones that are being tracked, needs the WHERE fixed
            ArrayList<String> args = new ArrayList<String>();
            ResultSet rs = dbconn.getResultSetSelect(select);
            while(rs.next()){
                acceptedobList.add(new acceptedTable(rs.getString("title"), rs.getString("fullname"), rs.getString("abstract")));
            }
            dbconn.close();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }

        accepted_col_capstone.setCellValueFactory(new PropertyValueFactory<>("title"));
        accepted_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        accepted_col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));

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
    private TableColumn<acceptedTable, String> invited_col_abstract;

    ObservableList<acceptedTable> invitedobList = FXCollections.observableArrayList();

    @FXML protected void handleInvitedLoadButtonAction(ActionEvent event){
        try{
            Database dbconn = new Database();
            String select = "SELECT capstone.title, users.fullname, capstone.abstract FROM capstone" +
                    "        JOIN users ON capstone.capstoneid = users.capstoneid" +
                    "        JOIN committee ON capstone.capstoneid = committee.capstoneid" +
                    "        WHERE was_invited = 1";//selects capstones that are being tracked, needs the WHERE fixed
            ResultSet rs = dbconn.getResultSetSelect(select);
            while(rs.next()){
                acceptedobList.add(new acceptedTable(rs.getString("title"), rs.getString("fullname"), rs.getString("abstract")));
            }
            dbconn.close();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }

        invited_col_capstone.setCellValueFactory(new PropertyValueFactory<>("title"));
        invited_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        invited_col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));

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
        Database dbconn = new Database();
        String update = "UPDATE committee SET tracking = 1 WHERE capstoneid = ?";
        ArrayList<String> args = new ArrayList<String>();
        args.add(capid);
        dbconn.setData(update, args);
        dbconn.close();
    }



    //view tab info
    @FXML
    private TableView<viewTable> facultyViewTable;
    @FXML
    private TableColumn<viewTable, String>  col_capstoneid;
    @FXML
    private TableColumn<viewTable, String> col_studentName;
    @FXML
    private TableColumn<viewTable, String> col_abstract;


    ObservableList<viewTable> viewobList = FXCollections.observableArrayList();


    @FXML protected void handleViewViewButtonAction(){
        String capid = "";
        if(viewText.getText() != null && viewText.getText() != ""){
            capid = viewText.getText();
        }
        try {
            Database dbconn = new Database();
            String select = "SELECT capstoneid, username, abstract FROM capstone WHERE capstoneid = ?;";
            ArrayList<String> args = new ArrayList<String>();
            args.add(capid);
            ResultSet rs = dbconn.getResultSet(select, args);
            while(rs.next()){
                viewobList.add(new viewTable(rs.getString("capstoneid"), rs.getString("username"), rs.getString("abstract")));
            }
            dbconn.close();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        col_capstoneid.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));

        facultyViewTable.setItems(viewobList);
    }

}
