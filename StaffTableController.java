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

   
    ObservableList<staffHomeTable> homeobList = FXCollections.observableArrayList();


@FXML protected void HandleCapstoneLoad(){
          try {
            Database dbconn = new Database();
            String select = "SELECT fullName,title,abstract,plagerismscore,grade FROM capstone JOIN users on capstone.username = users.username WHERE( capstone.title != ?);";
            ArrayList<String> args = new ArrayList<String>();
            args.add("-1");
            ResultSet rs = dbconn.getResultSet(select, args);
            System.out.println(rs);
            while(rs.next()){
               // homeobList.add(new staffHomeTable(rs.getString("fullName"), rs.getString("title"), rs.getString("abstract")));
            }
            dbconn.close();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
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
   }
