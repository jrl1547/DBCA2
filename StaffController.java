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
    
    @FXML private TableView updateStudentProjectTable,updateStudentInfoTable;
                  
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
    


   @FXML private TableView<staffStudentHistory> studentHistoryTable;
   @FXML
    private TableColumn<staffStudentHistory, String>  hist_col_studentName;

    ObservableList<staffHomeTable> viewobList = FXCollections.observableArrayList();

    
   
    @FXML protected void HandleCapstoneLoad(ActionEvent event){
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
     Capstone viewCap = new Capstone();
     String user = "";
        //Check if the text box has anything in it
        if(studentHistorySeachText.getText() != null && studentHistorySeachText.getText() != ""){
            user = studentHistorySeachText.getText();
        }
        

     ArrayList<ArrayList<String>> students = viewCap.getStudentHistoryName(user);
     staffStuHist.add(new staffStudentHistory(students.get(0).get(0)));
     hist_col_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));

     studentHistoryTable.setItems(staffStuHist);
     }


    @FXML protected void HandleUpdateStudentSearchStudentButtonAction(){
    //function to handle updateSearchStudentButton
    return;
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
    
    

    
    




