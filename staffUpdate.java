/**
 * Created by rs7386 on 4/19/2018.
 */
public class staffUpdate {

    String username,title,status,plagiarismscore;

    public staffUpdate(String username, String title, String status) {
        this.username = username;
        this.status = status;
            }

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getplagiarismscore() {
        return plagiarismscore;
    }

   public void setplagiarismscore(String plagiarismscore) {
        this.title = plagiarismscore;
    }




    }
