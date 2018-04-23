/**
 * Created by rs7386 on 4/19/2018.
 */
public class staffHomeTable {

    String name,title,abstrac,status,pscore,grade;

    public staffHomeTable(String name,String title,String abstrac) {
        this.name = name;
        this.title = title;
        this.abstrac = abstrac;
        this.status = status;
        this.pscore = pscore;
        this.grade = grade;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
