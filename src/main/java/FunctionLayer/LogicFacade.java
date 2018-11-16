package FunctionLayer;

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
    
    
    
    public static List<Material> updateBomCarport(Carport carport) {
        //Carport carport = new Carport(5.0, 3.0, new Roof(true));
        System.out.println(carport.getHeigth());
        
        Calculator calc = new Calculator();
        calc.countPostsForCarport(carport);
        calc.countRemForCarport(carport);
        calc.countBracketsForCarport(carport);
        calc.countScrewsForBrackets(carport);
        calc.countRaftersForRoof(carport);
        calc.countScrewsForRaftersFlatRoof(carport);
        calc.calculateRoofDimensions(carport);
        calc.countEaves(carport);
        
        System.out.println("TEST");
        System.out.println(carport.getMaterialListCarport().size());
        
        return carport.getMaterialListCarport();
        //BillOfMaterial bom = new BillOfMaterial();
        //bom.setBomCarport(carport.getMaterialListCarport());
    }
    
}
