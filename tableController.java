import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

/**
 * Created by cjcot on 4/14/2018.
 */
public class tableController implements Initializable {

    @FXML
    private TableView<ModelTable> facultyViewTable;
    @FXML
    private TableColumn<ModelTable, String>  col_capstoneid;
    @FXML
    private TableColumn<ModelTable, String> col_studentName;
    @FXML
    private TableColumn<ModelTable, String> col_abstract;
    @FXML
    private TextField viewText;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        String capid = "";
        if(viewText.getText() != null || viewText.getText() != ""){
            capid = viewText.getText();
        }
        try {
            Database dbconn = new Database();
            String select = "SELECT capstoneid, studentname, desc FROM capstone WHERE capstoneid = ?;";
            ResultSet rs = dbconn.getData(select, capid);
            while(rs.next()){
                oblist.add(new ModelTable(rs.getString("capstoneid"), rs.getString("studentname"), rs.getString("desc")));
            }
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        col_capstoneid.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_abstract.setCellValueFactory(new PropertyValueFactory<>("abstrac"));

        facultyViewTable.setItems(oblist);
    }
}
