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


public class StaffController{
   @FXML private TextField studentHistorySeachText,
   updateStudentSearchStudentText,updateStudentPlagiarismScore,updateStudentEmail,
   updateStudentFN,updateStudentLN;
   
    @FXML private ComboBox updateStudentComboMenu;
    
    @FXML private TableView updateStudentProjectTable,updateStudentInfoTable,
                  studentHistoryTable;
                  
   @FXML
    private TableView<staffHomeTable> capstonesTable;
    @FXML
    private TableColumn<staffHomeTable, String>  col_studentName;
    @FXML
    private TableColumn<staffHomeTable, String> col_projectTitle;
    @FXML
    private TableColumn<staffHomeTable, String>  col_abstract;
  //  @FXML
  //  private TableColumn<staffHomeTable, String> col_status;
  //  @FXML
  //  private TableColumn<staffHomeTable, String>  col_plagarismScore;
  //  @FXML
 //   private TableColumn<staffHomeTable, String>  col_grade;
    

    ObservableList<staffHomeTable> viewobList = FXCollections.observableArrayList();

    
   
    @FXML protected void HandleCapstoneLoad(ActionEvent event){
    System.out.println("pressed load button");
     
     // String capid = "1";
      Capstone viewCap = new Capstone();
        ArrayList<ArrayList<String>> capToView = viewCap.getCapstonesStaff();
        //System.out.println("got to add");
        viewobList.add(new staffHomeTable(capToView.get(0).get(0),capToView.get(0).get(1),capToView.get(0).get(2)));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_projectTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        
        capstonesTable.setItems(viewobList);
        
        }
     
    @FXML protected void HandleStudentHistorySearchButtonAction(){
     
     //function to handle studentHistoryHistorySearch
     return;
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
    
    

   }
    
    

    
    




