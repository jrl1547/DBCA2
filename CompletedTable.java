/**
 * Created by cjcot on 4/25/2018.
 */
public class CompletedTable {
    String capstone, name, username, abstrac, pscore, grade;

    public CompletedTable(String capstone, String name, String username, String abstrac, String pscore, String grade) {
        this.capstone = capstone;
        this.name = name;
        this.username = username;
        this.abstrac = abstrac;
        this.pscore = pscore;
        this.grade = grade;
    }

    public String getCapstone() {
        return capstone;
    }

    public void setCapstone(String capstone) {
        this.capstone = capstone;
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
