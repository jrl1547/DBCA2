/**
 * Created by cjcot on 4/15/2018.
 */
public class trackTable {
    String id;
    String name;
    String abstrac;
    String lastUpdate;

    public trackTable(String id, String name, String abstrac, String lastUpdate) {
        this.id = id;
        this.name = name;
        this.abstrac = abstrac;
        this.lastUpdate = lastUpdate;
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
}
