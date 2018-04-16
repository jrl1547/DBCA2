import java.util.*;


public class StudentDetails{

    String username;
    String mastersstart;
    String capstonestart;
    Database capstone_project;

    /**********************************************************************************
     *                                   CONSTRUCTORS                                  *
     **********************************************************************************/


    public StudentDetails(){
        capstone_project = new Database();
    }

    public StudentDetails(String _username){
        this();

        username = _username;
    }

    public StudentDetails(String _username, String _mastersstart, String _capstonestart){
        this();
        mastersstart  = _mastersstart;
        capstonestart = _capstonestart;
    }

    /**********************************************************************************
     *                                   ACCESSORS                                     *
     **********************************************************************************/
    String getUsername(){
        return username;
    }

    String getMastersstart(){
        return mastersstart;
    }

    String getCapstonestart(){
        return capstonestart;
    }




    /**********************************************************************************
     *                                   MUTATORS                                      *
     **********************************************************************************/
    void setUsername(String _username){
        username = _username;
    }

    void setMastersstart(String _mastersstart){
        mastersstart = _mastersstart;
    }

    void setCapstonestart(String _capstonestart){
        capstonestart = _capstonestart;
    }


    /**********************************************************************************
     *                                   METHODS                                       *
     **********************************************************************************/
    public ArrayList<ArrayList<String>> fetch(String _username){
        ArrayList<String> args = new ArrayList<String>();
        String select = "SELECT * from studentdetails WHERE username = ?;";
        args.add(_username);
        ArrayList<ArrayList<String>> fetchData = capstone_project.getData(select, args);
        fetchData.remove(0);
        username = fetchData.get(0).get(0);
        mastersstart = fetchData.get(0).get(1);
        capstonestart = fetchData.get(0).get(2);
        return fetchData;
    }

    public boolean put(){
        ArrayList<String> args = new ArrayList<String>();
        args.add(mastersstart);
        args.add(capstonestart);
        args.add(username);
        String update = "UPDATE studentdetails SET mastersstart = ?, capstonestart = ? WHERE username = ?;";
        boolean put = capstone_project.setData(update, args);
        return put;
    }

    public boolean post(){
        ArrayList<String> args = new ArrayList<String>();
        args.add(username);
        args.add(mastersstart);
        args.add(capstonestart);
        String insert = "INSERT INTO studentdetails (username, mastersstart, capstonestart) VALUES (?, ?, ?);";
        boolean post = capstone_project.setData(insert, args);
        return post;
    }

    public boolean delete(){
        ArrayList<String> args = new ArrayList<String>();
        args.add(username);
        String delete = "DELETE FROM studentdetails WHERE username = ?";
        boolean del = capstone_project.setData(delete, args);
        return del;
    }

    public String getCapstoneId(){
        ArrayList<String> args = new ArrayList<String>();
            args.add(username);

        String select = "SELECT capstoneId FROM capstone WHERE username = ?;";
        ArrayList<ArrayList<String>> fetchData = capstone_project.getData(select, args);
        fetchData.remove(0);
        if(!fetchData.isEmpty()) {
            return fetchData.get(0).get(0);
        } else {
            return null;
        }
    }

}