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

    public String getTypeName(String typeId){
        String query = "SELECT type FROM types WHERE typeid = " + typeId;
        ArrayList<ArrayList<String>> data = db.getData(query);
        if (!data.isEmpty()) {
            data.remove(0);
            if (!data.isEmpty()) {
                return data.get(0).get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


}
