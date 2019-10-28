package sample;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash class for data hashing
 */
public class Hash {
    /**
     * This method will encrypt value using MD5. It is one way encryption
     * @param pass String
     * @return String encryped value or empty in case of exception
     */
    public static String cryptWithMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
