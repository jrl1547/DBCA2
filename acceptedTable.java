/**
 * Created by cjcot on 4/15/2018.
 */
public class acceptedTable {
    String title, name, username, abstrac, status, grade;

    public acceptedTable(String title, String name, String username, String abstrac, String status, String grade) {
        this.title = title;
        this.name = name;
        this.username = username;
        this.abstrac = abstrac;
        this.status = status;
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
