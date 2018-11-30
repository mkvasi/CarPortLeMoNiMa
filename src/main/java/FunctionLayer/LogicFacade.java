package FunctionLayer;

//import FunctionLayer.calculators.OfferPriceCalculator;
import DBAccess.DataMapper;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import java.util.TreeMap;
import FunctionLayer.BillOfMaterial;

public class LogicFacade {

//CRUD - DATABASE
    
//BUSINESS LOGIC    
    public static Carport makeCarport(double length, double width, int roofAngle, double shedLength, double shedWidth) {
        if(shedLength > 0.0 && shedWidth > 0.0){
            Carport carport = new Carport(length, width, new Roof(roofAngle), new Shed(width, length));
            carport.getRoof().calculateRoofDimensions(carport);
            return carport;
        } else {
            Carport carport = new Carport(length, width, new Roof(roofAngle), null);
            carport.getRoof().calculateRoofDimensions(carport);
            return carport;
        }
    }
    
    public static BillOfMaterial makeBillOfMaterial(){
        BillOfMaterial billOfMaterial = new BillOfMaterial();
        //MANGLER AT BLIVE KODET!!!
        return billOfMaterial;
    }
    
    public static Price makePrice(BillOfMaterial billOfMaterial) throws MaterialException {
        Price price = new Price();
        price.calculateBuyPrice(billOfMaterial);
        price.calculateSellPrice(billOfMaterial);
        return price;
    }
    
    public static HashMap<Integer, TreeMap<Double, Material>> getAllBoards() throws MaterialException {
        MaterialDefault materialDefault = new MaterialDefault();
        materialDefault.setMaterialListFromDB(DataMapper.getMaterialList());
        materialDefault.addMaterialsToList(DataMapper.getFirstDefaultMaterial());
        return materialDefault.getAllMaterialList();
        //return DataMapper.getAllBoardsForThisCarportWithOutLengthCalculation();
    }
    
//    public static double getBuyPrice(BillOfMaterial billOfMaterial) throws MaterialException {
//        Price price = new Price();
//        price.calculateBuyPrice(billOfMaterial);
//        return price.getBuyprice();
//    }
//
//    public static double getSellPrice(BillOfMaterial billOfMaterial) throws MaterialException {
//        Price price = new Price();
//        price.calculateSellPrice(billOfMaterial);
//        return price.getSellprice();
//    }

//    public static HashMap<String, Material> getAllMaterialsFromDB(Carport carport) throws MaterialException {
//        //HashMap<String, Material> lort = DataMapper.getMaterialList();
//        ListToMapConvert listToMap = new ListToMapConvert();
//        HashMap<String, Material> lort = listToMap.listToHashMapMaterials(DataMapper.getMaterialList());
//        carport.setMaterialsToUseForThisCarport(lort);
//        return lort;
//
//    }
//        public static HashMap<String, Material> getAllMaterialsFromDB(Carport carport) throws MaterialException {
//        //HashMap<String, Material> lort = DataMapper.getMaterialList();
//        ListToMapConvert listToMap = new ListToMapConvert();
//        HashMap<String, Material> mapMaterialsFromDB = listToMap.listToHashMapMaterials(DataMapper.getMaterialList());
//        return mapMaterialsFromDB;
//
//    }
//    public static void getAllMaterialsFromDB(BillOfMaterial billOfMaterial) throws MaterialException {
//        //HashMap<String, Material> lort = DataMapper.getMaterialList();
//        ListToMapConvert listToMap = new ListToMapConvert();
//        HashMap<String, Material> mapMaterialsFromDB = listToMap.listToHashMapMaterials(DataMapper.getMaterialList());
//        
//
//    }
//
//    public static HashMap<String, Material> getDoneCarportWithMaterialList(Carport carport) throws MaterialException {
//        MaterialQtyCalculator calc = new MaterialQtyCalculator();
//        return calc.getDoneCarportWithMaterialQty(carport, getAllBoards());
//    }

//    public static double getOfferPrice(BillOfMaterial bill) throws MaterialException {
////        OfferPriceCalculator calc = new OfferPriceCalculator();
////        return calc.calculateOfferPrice(bill);
//        Price price = new Price();
//        price.
//    }
    

//    public static HashMap<Integer, TreeMap<Double, Material>> getAllBoards() throws MaterialException {
//        MaterialDefault materialDefault = new MaterialDefault();
//        materialDefault.setMaterialListFromDB(DataMapper.getMaterialList());
//        return materialDefault.getAllMaterialList();
//        //return DataMapper.getAllBoardsForThisCarportWithOutLengthCalculation();
//    }

//       public static Carport makeCarport(double length, double width, int roofAngle, double shedLength, double shedWidth) {
//        Roof roofDone = new Roof(roofAngle); 
//        Shed shed = new Shed(width, length);
//        Carport carport = new Carport (length, width, roofDone, shed); 
//        return carport;
//    }
    
       
//       public static void main(String[] args) throws MaterialException {
//       HashMap<Integer, TreeMap<Double,Material>> hej = getAllBoards(); 
//           System.out.println(hej.toString());
//    }

}
