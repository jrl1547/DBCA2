/**
 * Created by rs7386 on 4/19/2018.
 */

public class staffStudentHistory {
    String date, username, title, status, desc;

    public staffStudentHistory(String date, String username, String title, String status, String desc) {
        this.date = date;
        this.username = username;
        this.title = title;
        this.status = status;
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
