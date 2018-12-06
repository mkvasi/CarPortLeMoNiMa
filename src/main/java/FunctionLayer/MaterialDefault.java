package FunctionLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class MaterialDefault {

    private List<Material> materialListFromDB;
    private HashMap<Integer, TreeMap<Double, Material>> allMaterialMap;

    public MaterialDefault() {
        this.materialListFromDB = new ArrayList();
        this.allMaterialMap = new HashMap();
    }

    public List<Material> getMaterialListFromDB() {
        return materialListFromDB;
    }

    public void setMaterialListFromDB(List<Material> materialListFromDB) {
        this.materialListFromDB = materialListFromDB;
    }

    public HashMap<Integer, TreeMap<Double, Material>> getAllMaterialList() {
        return allMaterialMap;
    }

    public void setAllMaterialList(HashMap<Integer, TreeMap<Double, Material>> allMaterialList) {
        this.allMaterialMap = allMaterialList;
    }


}
