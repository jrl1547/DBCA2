import java.util.ArrayList;

public class ProjectTypes {
    Database db;

    public ProjectTypes(){
        db = new Database();
    }
    /**
     * Get types and append them to a lookup 2d array
     */
    public ArrayList<ArrayList<String>> getTypes(){
        String query = "SELECT * FROM types";
        return db.getData(query);
    }


}
