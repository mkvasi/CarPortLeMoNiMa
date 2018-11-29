package FunctionLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class MaterialType {

    private List<Material> materialListFromDB;
    private HashMap<Integer, TreeMap<Double, Material>> allMaterialList;

    public MaterialType() {
        this.materialListFromDB = new ArrayList();
        this.allMaterialList = new HashMap();
    }

    public List<Material> getMaterialListFromDB() {
        return materialListFromDB;
    }

    public void setMaterialListFromDB(List<Material> materialListFromDB) {
        this.materialListFromDB = materialListFromDB;
    }

    public HashMap<Integer, TreeMap<Double, Material>> getAllMaterialList() {
        return allMaterialList;
    }

    public void setAllMaterialList(HashMap<Integer, TreeMap<Double, Material>> allMaterialList) {
        this.allMaterialList = allMaterialList;
    }

    public void addMaterialsToList(int type_id, double width, double height) {
        TreeMap<Double, Material> boardsType = new TreeMap();
        
        for (Material material : materialListFromDB) {
            if(material.getType_id() == type_id && material.getWidth() == width && material.getHeight() == height){
                boardsType.put(material.getLength(), material);
            }   
        }
        
        allMaterialList.put(type_id, boardsType);
    }

}
