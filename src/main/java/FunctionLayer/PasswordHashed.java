package FunctionLayer;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The purpose of PasswordHashedSHA256: !!!TYPE PURPOSE OF PasswordHashedSHA256 HERE!!!
 * @author Morten
 * @version 1.0
 * @since 27-11-2018
 */

public class PasswordHashed {
    
    private static final String ENCODING_SHA256 = "SHA-256";
    
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
    
}
