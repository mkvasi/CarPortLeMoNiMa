package FunctionLayer;

//import FunctionLayer.calculators.OfferPriceCalculator;
import FunctionLayer.calculators.MaterialQtyCalculator;
import DBAccess.DataMapper;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import java.util.TreeMap;

public class LogicFacade {

    public static HashMap<String, Material> getAllMaterialsFromDB(Carport carport) throws MaterialException {
        //HashMap<String, Material> lort = DataMapper.getMaterialList();
        ListToMapConvert listToMap = new ListToMapConvert();
        HashMap<String, Material> lort = listToMap.listToHashMapMaterials(DataMapper.getMaterialList());
        carport.setMaterialsToUseForThisCarport(lort);
        return lort;

    }

    public static HashMap<String, Material> getDoneCarportWithMaterialList(Carport carport) throws MaterialException {
        MaterialQtyCalculator calc = new MaterialQtyCalculator();
        return calc.getDoneCarportWithMaterialQty(carport, getAllBoards());
    }

//    public static double getOfferPrice(BillOfMaterial bill) throws MaterialException {
////        OfferPriceCalculator calc = new OfferPriceCalculator();
////        return calc.calculateOfferPrice(bill);
//        Price price = new Price();
//        price.
//    }
    
    public static double getBuyPrice(BillOfMaterial billOfMaterial) throws MaterialException {
        Price price = new Price();
        price.calculateBuyPrice(billOfMaterial);
        return price.getBuyprice();
    }
    
    public static double getSellPrice(BillOfMaterial billOfMaterial) throws MaterialException {
        Price price = new Price();
        price.calculateSellPrice(billOfMaterial);
        return price.getSellprice();
    }

    public static HashMap<Integer, TreeMap<Double, Material>> getAllBoards() throws MaterialException {
        MaterialType materialType = new MaterialType();
        materialType.setMaterialListFromDB(DataMapper.getMaterialList());
        return materialType.getAllMaterialList();
        //return DataMapper.getAllBoardsForThisCarportWithOutLengthCalculation();
    }

}


