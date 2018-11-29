package FunctionLayer;

import java.util.HashMap;
import java.util.List;

/**
 * The purpose of ListToMapConvert is to have an entity to convert a List to a Map
 * HashMap
 *
 * @author Morten
 * @version 1.0
 * @since 27-11-2018
 */
public class ListToMapConvert {

    public HashMap<String, Material> listToHashMapMaterials(List<Material> listOfMaterials) {
        HashMap<String, Material> materialList = new HashMap();

        for (Material material : listOfMaterials) {
            materialList.put(material.getDescription() + material.getLength(), material);
        }

        return materialList;
    }
}
