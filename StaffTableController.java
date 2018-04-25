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


public class StaffTableController{

//landing page
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

   // history table declarations
   @FXML
    private TableView<staffStudentHistory> studentHistoryTable;
   @FXML
    private TableColumn<staffStudentHistory, String>  hist_col_studentName;

    

    ObservableList<staffHomeTable> homeobList = FXCollections.observableArrayList();

//landing page load button

@FXML protected void HandleCapstoneLoad(){
          try {
        System.out.println("this executed");
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        col_projectTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));
        col_plagarismScore.setCellValueFactory(new PropertyValueFactory<>("pscore"));
        col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        col_faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        col_declined.setCellValueFactory(new PropertyValueFactory<>("declined"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        


        capstonesTable.setItems(homeobList);
        }

        catch(Exception e){
            e.printStackTrace();
        }
       }
       
       //search button
       ObservableList<staffStudentHistory> histobList = FXCollections.observableArrayList();

   @FXML protected void HandleStudentHistorySearchButtonAction(){
          try {
        hist_col_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));        
        


        studentHistoryTable.setItems(histobList);
        }

        catch(Exception e){
            e.printStackTrace();
        }
       }
       
       
       
   
   
   }
