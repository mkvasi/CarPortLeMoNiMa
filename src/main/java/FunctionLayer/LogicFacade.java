package FunctionLayer;

import FunctionLayer.calculators.OfferPriceCalculator;
import FunctionLayer.calculators.MaterialQtyCalculator;
import DBAccess.MaterialMapper;
import FunctionLayer.exceptions.MaterialException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.TreeMap;

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

    public static HashMap<String, Material> getAllMaterialsFromDB(Carport carport) throws MaterialException {
        HashMap<String, Material> lort = MaterialMapper.getMaterialList();
        carport.setMaterialsToUseForThisCarport(lort);
        return lort;

    }

    public static HashMap<String, Material> getDoneCarportWithMaterialList(Carport carport) throws MaterialException {
        MaterialQtyCalculator calc = new MaterialQtyCalculator();
        return calc.getDoneCarportWithMaterialQty(carport, getAllBoards());
    }

    public static double getOfferPrice(BillOfMaterial bill) throws MaterialException {
        OfferPriceCalculator calc = new OfferPriceCalculator();
        return calc.calculateOfferPrice(bill);
    }

    public static HashMap<Integer, TreeMap<Double, Material>> getAllBoards() throws MaterialException {
        return MaterialMapper.getAllBoardsForThisCarportWithOutLengthCalculation();
    }

}


