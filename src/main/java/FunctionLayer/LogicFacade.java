package FunctionLayer;

import DBAccess.MaterialMapper;
import FunctionLayer.exceptions.MaterialException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class LogicFacade {
    
    private static final String ENCODING_SHA256 = "SHA-256";
    
//SHA256 AF PASSWORD
    public static String encodePasswordSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(ENCODING_SHA256);
        byte[] encodedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder passwordSHA256 = new StringBuilder();
        for (int i : encodedPassword) {
            if (Integer.toHexString(0xFF & i).length() == 2) {
                passwordSHA256.append(Integer.toHexString(0xFF & i));
            } else {
                passwordSHA256.append(0x00).append(Integer.toHexString(0xFF & i));
            }
        }
        return new String(passwordSHA256);
    }
    
//DATAMAPPER CRUD
    
//BUSINESS LOGIC
    
    
 
    
    
//    
//    public static List<Material> updateBomCarport(Carport carport) {
//        //Carport carport = new Carport(5.0, 3.0, new Roof(true));
//        System.out.println(carport.getHeigth());
//        
//        MaterialQtyCalculator calc = new MaterialQtyCalculator();
//        calc.countPostsForCarport(carport);
//        calc.countRemForCarport(carport);
//        calc.countBracketsForCarport(carport);
//        calc.countScrewsForBrackets(carport);
//        calc.countRaftersForRoof(carport);
//        calc.countScrewsForRafters(carport);
//        calc.calculateRoofDimensions(carport);
//        calc.countEaves(carport);
//        
//        System.out.println("TEST");
//        System.out.println(carport.getMaterialListCarport().size());
//        
//        return carport.getMaterialListCarport();
//        //BillOfMaterial bom = new BillOfMaterial();
//        //bom.setBomCarport(carport.getMaterialListCarport());
//    }
    
    

//
//    public static BillOfMaterial getBom(Carport carport) throws MaterialException {
//        MaterialMapper.getMaterialById(1);
//        return null;
//    }

    public static List getMaterialList() throws MaterialException {
        //Giv carport med som parameter, også check på skur og roof, og hent materialer præcis udfra det. 
        //således at der er 4 mappers, derfor kaldes der kun en gang til DB, for hele listen, også tilføjes mængden i lommeregneren
        //i stedet for at kalde DB pr. enkelt materiale
        return MaterialMapper.getMaterialList(); 
        
    }
    
    public static List getMaterialListWithQty(List materialList, Carport carport){
        
        CarportCalculator calc = new CarportCalculator();
        return calc.getCarportBOM(materialList, carport);
    }
    
}
