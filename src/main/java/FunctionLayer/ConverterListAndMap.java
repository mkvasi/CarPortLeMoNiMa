package FunctionLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The purpose of ConverterListAndMap is to have an entity to convert a List to a Map
 HashMap
 *
 * @author Morten
 * @version 1.0
 * @since 27-11-2018
 */
public class ConverterListAndMap {
    
   

    public HashMap<Integer,TreeMap<Double, Material>> ListToHashMap(List<Material> listOfMaterials) {
        HashMap<Integer, TreeMap<Double, Material>> materialMap = new HashMap();
        TreeMap <Double, Material> materialTreeMap6 = new TreeMap(); 
        TreeMap <Double, Material> materialTreeMap10 = new TreeMap(); 
        TreeMap <Double, Material> materialTreeMap12 = new TreeMap(); 
        TreeMap <Double, Material> materialTreeMap13 = new TreeMap(); 
        TreeMap <Double, Material> materialTreeMap7 = new TreeMap(); 
        TreeMap <Double, Material> materialTreeMap14 = new TreeMap(); 
        
        
        for (Material material : listOfMaterials) {
           int type_id = material.getType_id(); 
            switch(type_id){
                case 6:
                    materialTreeMap6.put(material.getLength(), material);
                    materialMap.put(type_id, materialTreeMap6);  
                break;
                case 10: 
                     materialTreeMap10.put(material.getLength(), material);
                    materialMap.put(type_id, materialTreeMap10); 
                break;
                case 12:
                        materialTreeMap12.put(material.getLength(), material);
                    materialMap.put(type_id, materialTreeMap12); 
                break;
                case 13:
                        materialTreeMap13.put(material.getLength(), material);
                    materialMap.put(type_id, materialTreeMap13); 
                break;
                case 7:
                        materialTreeMap7.put(material.getLength(), material);
                    materialMap.put(type_id, materialTreeMap7); 
                break;
                case 14:
                        materialTreeMap14.put(material.getLength(), material);
                    materialMap.put(type_id, materialTreeMap14); 
                break;
                
            }
        }


        return materialMap;
                    
        }

    
    public List<Material> hashMaptoList(HashMap<String, Material> mapOfMaterials) {
        List<Material> materialList = new ArrayList();

        for (Map.Entry<String, Material> entry : mapOfMaterials.entrySet()) {
            String key = entry.getKey();
            Material value = entry.getValue();
            materialList.add(value);
        }
        return materialList;
        
    }
}
