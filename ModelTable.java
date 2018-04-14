/**
 * Created by cjcot on 4/14/2018.
 */
public class ModelTable {

    String id, name, abstrac;

    public ModelTable(String id, String name, String abstrac) {
        this.id = id;
        this.name = name;
        this.abstrac = abstrac;
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
}
