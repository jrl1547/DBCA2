import java.util.*;
public class StatusHistory {

    String capstoneid;
    String sid;
    String date;
    Database capstone_project;


    /**********************************************************************************
     *                                   CONSTRUCTORS                                  *
     **********************************************************************************/

    public StatusHistory(){
        capstone_project = new Database();

    }

    public StatusHistory(String _capstoneid){
        capstone_project = new Database();

        capstoneid = _capstoneid;
    }

    public StatusHistory(String _capstoneid, String _sid, String _date){
        capstone_project = new Database();

        capstoneid = _capstoneid;
        sid        = _sid;
        date       = _date;
    }


    /**********************************************************************************
     *                                   ACCESSORS                                     *
     **********************************************************************************/
    String getCapstoneid(){
        return capstoneid;
    }

    String getSid(){
        return sid;
    }

    String getDate(){
        return date;
    }


    /**********************************************************************************
     *                                   MUTATORS                                      *
     **********************************************************************************/
    void setCapstoneid(String _capstoneid){
        capstoneid = _capstoneid;
    }

    void setSid(String _sid){
        sid = _sid;
    }

    void setDate(String _date){
        date = _date;
    }


    /**********************************************************************************
     *                                   METHODS                                       *
     **********************************************************************************/
    public ArrayList<ArrayList<String>> fetch(String _capstoneid){
        ArrayList<String> args = new ArrayList<String>();
        args.add(_capstoneid);
        String select = "SELECT * from statushistory WHERE capstoneid = ? ORDER BY date DESC";
        ArrayList<ArrayList<String>> fetchData = capstone_project.getData(select, args);

        if(!fetchData.isEmpty()) {
            fetchData.remove(0);
            if (!fetchData.isEmpty()) {
                sid = fetchData.get(0).get(1);
                capstoneid = fetchData.get(0).get(0);
                date = fetchData.get(0).get(2);
            }
        }
        return fetchData;
    }

    public boolean put(){
        ArrayList<String> args = new ArrayList<String>();
        args.add(sid);
        args.add(date);
        args.add(capstoneid);
        String update = "UPDATE statushistory SET sid = ?, date = ? WHERE capstoneid = ?;";
        boolean put = capstone_project.setData(update, args);
        return put;
    }

    public boolean post(){
        ArrayList<String> args = new ArrayList<String>();
        args.add(sid);
        args.add(capstoneid);
        String insert = "INSERT INTO statushistory (statusid, capstoneid) VALUES (?, ?);";
        boolean post = capstone_project.setData(insert, args);
        return post;
    }

    public boolean delete(){
        ArrayList<String> args = new ArrayList<String>();
        args.add(capstoneid);
        String delete = "DELETE FROM statushistory WHERE capstoneid = ?;";
        boolean del = capstone_project.setData(delete, args);
        return del;
    }

    public ArrayList<ArrayList<String>> getCapstoneHistory(String _capid){
        ArrayList<String> item = new ArrayList<String>();
        item.add(_capid);
        ArrayList<ArrayList<String>> fetchData = capstone_project.getData(
                "SELECT statushistory.date, capstone.username, capstone.title, status.name, status.description FROM status \n" +
                        "JOIN statushistory ON status.statusid = statushistory.statusid \n" +
                        "JOIN capstone ON statushistory.capstoneid = capstone.capstoneid\n" +
                        "WHERE capstone.capstoneid = ?;",item);
        fetchData.remove(0);

        return fetchData;

    }


}
