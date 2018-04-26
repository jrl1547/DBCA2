/**
 * Created by rs7386 on 4/19/2018.
 */
public class staffUpdate {

    String username,title,status,pscore;

    public staffUpdate(String username, String title, String status, String pscore) {
        this.username = username;
        this.title=title;
        this.pscore = pscore;
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
    
    public String getPscore() {
        return pscore;
    }

   public void setPScore(String pscore) {
        this.pscore = pscore;
    }




    }
