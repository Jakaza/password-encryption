package password.enryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Jakaza G Chauke
 */
public class PasswordUtils {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATION = 10000;
    private static final int KEY_LENGTH = 256;
    
    public static String getSalt(int length){
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < length; i++)
            salt.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));

        return new String(salt);
    }
    
    public static byte[] hash(char[] password , byte[] salt){
        PBEKeySpec pbe = new PBEKeySpec(password , salt,ITERATION, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory secretKeyFactory  = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        
            return secretKeyFactory.generateSecret(pbe).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) 
        {
            System.out.println("Error while hashing password : " + ex.getMessage());
        }finally{
            pbe.clearPassword();
        } 
        return null;
    }
    
    public static String generateSecuredPassword(String password, String salt){
        String securedPassword = null;
        byte[] hashedPassword;
        if(hash(password.toCharArray(),salt.getBytes()) != null){
            hashedPassword = hash(password.toCharArray(),salt.getBytes());
            securedPassword = Base64.getEncoder().encodeToString(hashedPassword);
            return securedPassword;
        }
        return securedPassword;
    }
    
    public static boolean verifyPassword(String providedPassword, String securedPassword, String salt){
        boolean status = false;
        String newSecuredPassword = generateSecuredPassword(providedPassword,salt);
        status = newSecuredPassword.equalsIgnoreCase(securedPassword);
        return status;
    }
}
