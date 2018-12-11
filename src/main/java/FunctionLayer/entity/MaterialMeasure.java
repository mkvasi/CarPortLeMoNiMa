package FunctionLayer.entity;

/**
 * The purpose of MaterialMeasure: !!!TYPE PURPOSE OF MaterialMeasure HERE!!!
 * @author Morten
 * @version 1.0
 * @since 29-11-2018
 */

public class MaterialMeasure {

    private int id;
    private String measure;

    public MaterialMeasure(int id, String measure) {
        this.id = id;
        this.measure = measure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
    
    
}
