/**
 * Created by cjcot on 4/15/2018.
 */
public class trackTable {
    String capname;
    String name;
    String username;
    String abstrac;
    String lastUpdate;
    String status;
    String pscore;
    String grade;

    public trackTable(String capname, String name, String username, String abstrac, String lastUpdate, String status, String pscore, String grade) {
        this.capname = capname;
        this.name = name;
        this.username = username;
        this.abstrac = abstrac;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.pscore = pscore;
        this.grade = grade;
    }

    public String getCapname() {
        return capname;
    }

    public void setCapname(String capname) {
        this.capname = capname;
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

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
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
