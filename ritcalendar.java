import java.util.*;

public class RITCalendar{
    String term;
    String startDate;
    String addDropDeadline;
    String gradeDeadline;
    String endDate;
    Database capstone_project;

    /**********************************************************************************
     *                                   CONSTRUCTORS                                  *
     **********************************************************************************/
    public RITCalendar(){
        capstone_project = new Database();
    }

    public RITCalendar(String term){
        capstone_project = new Database();
        this.term = term;
    }

    public RITCalendar(String _term, String _startDate, String _addDropDeadline, String _gradeDeadline, String _endDate) {
        capstone_project = new Database();
        term = _term;
        startDate = _startDate;
        addDropDeadline = _addDropDeadline;
        gradeDeadline = _gradeDeadline;
        endDate = _endDate;
    }


    /**********************************************************************************
     *                                   ACCESSORS                                     *
     **********************************************************************************/
    public String getStartDate(){
        return startDate;
    }

    public String getAddDropDeadline(){
        return this.addDropDeadline;
    }

    public String getGradeDeadline(){
        return this.gradeDeadline;
    }

    public String getEndDate(){
        return this.endDate;
    }


    /**********************************************************************************
     *                                   MUTATORS                                      *
     **********************************************************************************/
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public void setAddDropDeadline(String addDropDeadline){
        this.addDropDeadline = addDropDeadline;
    }

    public void setGradeDeadline(String gradeDeadline){
        this.gradeDeadline = gradeDeadline;
    }

    public void setEndDate(String endDate){
        this.endDate = endDate;
    }


    /**********************************************************************************
     *                                   METHODS                                       *
     **********************************************************************************/
    /*
       Fetch gets the data regarding the rit calendar for the term that was given in the constructor.
    */
    public ArrayList<ArrayList<String>> fetch(String _term){
        ArrayList<String> args = new ArrayList<String>();
        args.add(_term);
        String select = "SELECT * from ritcalendar WHERE _capstoneid = ?";
        ArrayList<ArrayList<String>> fetchData = capstone_project.getData(select, args);
        fetchData.remove(0);

        term            = fetchData.get(0).get(0).toString();
        startDate       = fetchData.get(0).get(1).toString();
        addDropDeadline = fetchData.get(0).get(2).toString();
        gradeDeadline   = fetchData.get(0).get(3).toString();
        endDate         = fetchData.get(0).get(4).toString();

        return fetchData;
    }

    /*
       update data for a currently existing term in the ritcalendar
    */
    public boolean put(){
        String update = "UPDATE ritcalendar SET startDate = ?, addDropDeadline = ?, gradeDeadline = ?, endDate = ? WHERE term = ?;";
        ArrayList<String> args = new ArrayList<String>();
        args.add(startDate);
        args.add(addDropDeadline);
        args.add(gradeDeadline);
        args.add(endDate);
        args.add(term);
        boolean put = capstone_project.setData(update, args);
        return put;
    }

    /*
       insert all of the associated data into the database
    */
    public boolean post(){
        String insert = "INSERT INTO ritcalendar SET(term, startDate, addDropDeadline, gradeDeadline, endDate) VALUES(?,?,?,?,?);";
        ArrayList<String> args = new ArrayList<String>();
        args.add(term);
        args.add(startDate);
        args.add(addDropDeadline);
        args.add(gradeDeadline);
        args.add(endDate);
        boolean post = capstone_project.setData(insert, args);
        return post;
    }

    /*
       delete all calendar data associated with the term given
    */
    public boolean delete(){
        String delete = "DELETE FROM ritcalendar WHERE term = ?;";
        ArrayList<String> args = new ArrayList<String>();
        args.add(term);
        boolean del = capstone_project.setData(delete, args);
        return del;
    }

}