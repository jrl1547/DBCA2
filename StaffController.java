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
import javafx.scene.control.ComboBox;


public class StaffController implements iUserController{
   @FXML private TextField studentHistorySeachText,
   updateStudentSearchStudentText,updateStudentPlagiarismScore,updateStudentEmail,
   updateStudentFN,updateStudentLN;
   
    @FXML private ComboBox updateStudentComboMenu;
    
   // @FXML private updateStudentInfoTable;
                  
   @FXML
    private TableView<staffHomeTable> capstonesTable;
    @FXML
    private TableColumn<staffHomeTable, String>  col_studentName;
    @FXML
    private TableColumn<staffHomeTable, String> col_projectTitle;
    @FXML
    private TableColumn<staffHomeTable, String>  col_abstract;
    @FXML
    private TableColumn<staffHomeTable, String> col_status;
    @FXML
    private TableColumn<staffHomeTable, String>  col_plagarismScore;
    @FXML
    private TableColumn<staffHomeTable, String>  col_grade;
    @FXML
    private TableColumn<staffHomeTable, String>  col_faculty;
   @FXML
    private TableColumn<staffHomeTable, String>  col_declined;
    

//student history table
   @FXML private TableView<staffStudentHistory> studentHistoryTable;
   @FXML
    private TableColumn<staffStudentHistory, String>  history_col_date;
    @FXML
    private TableColumn<staffStudentHistory, String>  history_col_username;
    @FXML
    private TableColumn<staffStudentHistory, String>  history_col_title;
    @FXML
    private TableColumn<staffStudentHistory, String>  history_col_status;
    @FXML
    private TableColumn<staffStudentHistory, String>  history_col_desc;

    
    
    //update tab
    @FXML private TableView<staffUpdate> updateStudentProjectTable;
    @FXML
    private TableColumn<staffUpdate, String>  update_col_student;
    @FXML
    private TableColumn<staffUpdate, String>  update_col_title;
    @FXML
    private TableColumn<staffUpdate, String>  update_col_status;
    @FXML
    private TableColumn<staffUpdate, String>  update_col_pscore;



    @FXML
    private TableView<CompletedTable> completedTable;
    @FXML
    private TableColumn<CompletedTable, String> completed_col_capstone;
    @FXML
    private TableColumn<CompletedTable, String> completed_col_name;
    @FXML
    private TableColumn<CompletedTable, String> completed_col_username;
    @FXML
    private TableColumn<CompletedTable, String> completed_col_abstract;
    @FXML
    private TableColumn<CompletedTable, String> completed_col_pscore;
    @FXML
    private TableColumn<CompletedTable, String> completed_col_grade;

    @FXML protected void HandleCompletedLoadButtonAction(ActionEvent event){
        ObservableList<CompletedTable> completedobList = FXCollections.observableArrayList();
        Capstone findCompleted = new Capstone();
        completedTable.setItems(completedobList);
        ArrayList<ArrayList<String>> data = findCompleted.getCompletedCapstones();
        for (int x = 0; x < data.size(); x+=2) {//goes through each row
            completedobList.add(new CompletedTable(data.get(x).get(0), data.get(x).get(1), data.get(x).get(2), data.get(x).get(3), data.get(x).get(4), data.get(x).get(5)));
        }

        completed_col_capstone.setCellValueFactory(new PropertyValueFactory<>("capstone"));
        completed_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        completed_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        completed_col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        completed_col_pscore.setCellValueFactory(new PropertyValueFactory<>("pscore"));
        completed_col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        completedTable.setItems(completedobList);
    }

    
   
    @FXML protected void HandleCapstoneLoad(ActionEvent event){
        ObservableList<staffHomeTable> viewobList = FXCollections.observableArrayList();
        capstonesTable.setItems(viewobList);
        System.out.println("pressed load button");
     
     // String capid = "1";
        Capstone viewCap = new Capstone();
        Committee viewCom = new Committee();
        ArrayList<ArrayList<String>> capToView = viewCap.getCapstonesStaff();
        ArrayList<ArrayList<String>> comToView = viewCom.getCommitteeFaculty();
        ArrayList<ArrayList<String>> comDeclined = viewCom.getDeclinedCommitteeFaculty();
        ArrayList<ArrayList<String>> statuses = viewCap.getCapstonesStatus();

        System.out.println("got to add");
        viewobList.add(new staffHomeTable(capToView.get(0).get(0),capToView.get(0).get(1),capToView.get(0).get(2),capToView.get(0).get(3),capToView.get(0).get(4),comToView.get(0).get(0),comDeclined.get(0).get(0),statuses.get(0).get(0)));
        System.out.println(viewobList);
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_projectTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        col_plagarismScore.setCellValueFactory(new PropertyValueFactory<>("pscore"));
        col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        col_faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        col_declined.setCellValueFactory(new PropertyValueFactory<>("declined"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        
        capstonesTable.setItems(viewobList);
        
        }
     ObservableList<staffStudentHistory> staffStuHist = FXCollections.observableArrayList();

    @FXML protected void HandleStudentHistorySearchButtonAction(){
     
     //function to handle studentHistoryHistorySearch
     System.out.println("pressed search button");
      String username = "";
        if(studentHistorySeachText.getText() != null && studentHistorySeachText.getText() != ""){
            username = studentHistorySeachText.getText();
        }
        Capstone getid = new Capstone();
        String capid = getid.getCapstoneId(username);
        StatusHistory history = new StatusHistory();
        ArrayList<ArrayList<String>> data = history.getCapstoneHistory(capid);
        for(int x = 0; x < data.size(); x++){
            staffStuHist.add(new staffStudentHistory(data.get(x).get(0), data.get(x).get(1), data.get(x).get(2), data.get(x).get(3), data.get(x).get(4)));
        }
        history_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        history_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        history_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        history_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        history_col_desc.setCellValueFactory(new PropertyValueFactory<>("desc"));

        studentHistoryTable.setItems(staffStuHist);

    }
     

    ObservableList<staffUpdate> staffUpdateOb = FXCollections.observableArrayList();

    @FXML protected void HandleUpdateStudentSearchStudentButtonAction(){
    //function to handle updateSearchStudentButton
    Capstone viewCap = new Capstone();
    String user = "";

    if(updateStudentSearchStudentText.getText() != null && updateStudentSearchStudentText.getText() != ""){
            user = updateStudentSearchStudentText.getText();
        }
        
      ArrayList<ArrayList<String>> table = viewCap.getStaffUpdate(user);  
      staffUpdateOb.add(new staffUpdate(table.get(0).get(0),table.get(0).get(1),table.get(0).get(2)));
      update_col_student.setCellValueFactory(new PropertyValueFactory<>("username"));
      update_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
      update_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
     // update_col_pscore.setCellValueFactory(new PropertyValueFactory<>("plagiarismscore"));
      updateStudentProjectTable.setItems(staffUpdateOb);

  
    }
    
    @FXML protected void HandleUpdateStudentPlagiarismScoreButtonAction(){
    //function to handle updateStudentPlagiarismScoreButton
    return;
    }
    
    
     @FXML protected void HandleUpdateStudentInfoButtonAction(){
    //function to handle updateStudentInfoButton
    return;
    }
    
    @FXML protected void HandleUpdateStudentComboMenuButtonAction(){
    //function to handle updateStudentComboMenuButton
    return;
    }
    
    @FXML protected void loadCapstoneTab(){
    //function to load capstone tab
    
        return;
    }
    
    @FXML protected void loadStudentHistoryTab(){
    //function to load student history tab
    
        return;
    }


     @FXML protected void loadStudentUpdateTab(){
    //function to load student Update tab
    
        return;
    }
    
     @Override
      public void setUsername(String username) {

    }

    
    

   }
    
    

    
    




