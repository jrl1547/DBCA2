/**
 * Created by rs7386 on 4/19/2018.
 * Editied by Matthew Fitzgerald (mjf7345) 4/26/18
 */
public class staffHomeTable {

    String name,title,abstrac,status,pscore,grade,startDate;

    public staffHomeTable(String name,String title,String abstrac, String status,String pscore, String grade,String startDate) {
        this.name = name;
        this.title = title;
        this.abstrac = abstrac;
        this.status = status;
        this.pscore = pscore;
        this.grade = grade;
        //this.faculty = faculty;
        //this.declined = declined;
        this.startDate = startDate;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String Faculty) {
        this.faculty = faculty;
    }

   public String getDeclined() {
        return declined;
    }

    public void setdeclined(String declined) {
        this.declined = declined;
    }
*/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstrac() {
        return abstrac;
    }

    public void setAbstrac(String abstrac) {
        this.abstrac = abstrac;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPscore() {
        return pscore;
    }

    public void setPscore(String pscore) {
        this.pscore = pscore;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getStartDate(){
        return startDate;
    }
    public void setStartDate(String grade) {
        this.startDate = startDate;
    }
}
