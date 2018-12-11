package FunctionLayer.entity;

/**
 * The purpose of MaterialType: !!!TYPE PURPOSE OF MaterialType HERE!!!
 * @author Morten
 * @version 1.0
 * @since 29-11-2018
 */

public class MaterialType {

    private int id;
    private String type;

    public MaterialType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
