/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Sanele
 */
public class clsPasswordAESCryptography {
    private static final String KEY = "p@($){$}[w]0|R|D";
    private static final String INITVECTOR = "~A#E*S[J)A(V]A|>";
    
    public static String mEncryptPassword(String password)
    {
        try{
            IvParameterSpec iv = new IvParameterSpec(INITVECTOR.getBytes("UTF-8"));
            SecretKey skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            
            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.encodeBase64String(encrypted);
            
        }catch(UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException eX){
            JOptionPane.showMessageDialog(null, eX);
        }
        return null;
    }
    
    public static String mDecryptPassword(String encryptedPassword)
    {
        try{
            IvParameterSpec iv = new IvParameterSpec(INITVECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
        
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] userGeneratedPassword = cipher.doFinal(Base64.decodeBase64(encryptedPassword));
            return new String(userGeneratedPassword);
        }catch(UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException eX){
            JOptionPane.showMessageDialog(null, eX);
        }
        return null;
    }
}
