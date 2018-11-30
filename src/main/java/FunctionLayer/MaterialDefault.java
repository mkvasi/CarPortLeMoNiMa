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

    public void addMaterialsToList(Material materialUsedDefault) {
        TreeMap<Double, Material> boardsType = new TreeMap();
        int type_id = materialUsedDefault.getType_id();

        for (Material material : materialListFromDB) {
            if (material.isDefaultUsed()) {
                if (type_id != material.getType_id()) {
                    allMaterialMap.put(type_id, boardsType);
                    boardsType = new TreeMap();
                    type_id = insertNewBoardsType(material, boardsType);
                } else {
//                    checkForNewBoardsType(boardsType);
                    type_id = insertNewBoardsType(material, boardsType);
                }
            }

        }
    }

    public int insertNewBoardsType(Material material, TreeMap<Double, Material> boardsType) {
        //type_id = material.getType_id();
        boardsType.put(material.getLength(), material);
        return material.getType_id();
    }

//    public TreeMap<Double, Material> checkForNewBoardsType(TreeMap<Double, Material> boardsType) {
//        if (boardsType == null) {
//            return new TreeMap();
//        }
//        return boardsType;
//    }
//    public void addMaterialsToList() {
//        TreeMap<Double, Material> boardsType = new TreeMap();
//        int type_id = 0;
//
//        for (Material material : materialListFromDB) {
//            if (material.isDefaultUsed()) {
//                if (type_id != 0) {
//                    if (type_id != material.getType_id()) {
//                        allMaterialMap.put(type_id, boardsType);
//                    }
//                } else {
//                    type_id = material.getType_id();
//                    boardsType.put(material.getLength(), material);
//                }
//            }
//
//        }
//
//        //allMaterialList.put(, boardsType);
//    }
//    public void addMaterialsToList(int type_id, double width, double height) {
//        TreeMap<Double, Material> boardsType = new TreeMap();
//        
//        for (Material material : materialListFromDB) {
//            if(material.getType_id() == type_id && material.getWidth() == width && material.getHeigth() == height){
//                boardsType.put(material.getLength(), material);
//            }   
//        }
//        
//        allMaterialMap.put(type_id, boardsType);
//    }
}
