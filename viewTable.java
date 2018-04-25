/**
 * Created by cjcot on 4/14/2018.
 */
public class viewTable {

    String id, name, username, abstrac, last_update, status, pscore, grade;

    public viewTable(String id, String name, String username, String abstrac, String pscore, String grade) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.abstrac = abstrac;
        this.pscore = pscore;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAbstrac() {
        return abstrac;
    }

    public void setAbstrac(String abstrac) {
        this.abstrac = abstrac;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
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
