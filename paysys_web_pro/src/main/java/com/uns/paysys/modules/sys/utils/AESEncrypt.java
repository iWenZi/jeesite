package com.uns.paysys.modules.sys.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AESEncrypt
{
    private static String hexStr =  "0123456789ABCDEF"; 

    private static String privateKey = "uns12Pay";
    
    private static Log log = LogFactory.getLog(AESEncrypt.class);
    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @return
     */
    public static String encrypt(String content)
    {
    	if(content == null || "".equals(content)){
    		return null;
    	}
        try
        {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );   
            secureRandom.setSeed(privateKey.getBytes()); 
            
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            String strResult = BinaryToHexString(result);
            return strResult; // 加密
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error("Invalid Algorith.");
            //e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            log.error(e.getMessage(),e);
        }
        catch (InvalidKeyException e)
        {
            log.error(e.getMessage(),e);
        }
        catch (UnsupportedEncodingException e)
        {
            log.error(e.getMessage(),e);
        }
        catch (IllegalBlockSizeException e)
        {
            log.error(e.getMessage(),e);
        }
        catch (BadPaddingException e)
        {
            log.error(e.getMessage(),e);
        }
        return null;
    }
   
    /**解密
     * @param content  待解密内容
     * @return
     */
    public static String decrypt(String content)
    {
    	if(content == null || "".equals(content)){
    		return "";
    	}
        try
        {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );   
            secureRandom.setSeed(privateKey.getBytes());   
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] byteContent = HexStringToBinary(content);

            byte[] result = cipher.doFinal(byteContent);
            return new String(result); // 加密
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error("Invalid Algorith.");
            //e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            log.error("Invalid Padding.");
        }
        catch (InvalidKeyException e)
        {
            log.error("Invalid key.");
        }
        catch (IllegalBlockSizeException e)
        {
            log.error("Illegal Block Size.");
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
            log.error("bad  Paddin.");
        }
        return null;
    }
    /**
     * 
     * @param bytes
     * @return 将二进制转换为十六进制字符输出
     */ 
    public static String BinaryToHexString(byte[] bytes)
    {
        
        StringBuilder  result = new StringBuilder();
        StringBuilder  hex = new StringBuilder();
        for (int i = 0; i < bytes.length; i++)
        {
            hex.delete(0, hex.length());
            // 字节高4位
            hex.append(String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4)));
            // 字节低4位
            hex.append(String.valueOf(hexStr.charAt(bytes[i] & 0x0F)));
            result.append(hex);

        }
        return result.toString();
    } 
    /**
     * 
     * @param hexString
     * @return 将十六进制转换为字节数组
     */ 
    public static byte[] HexStringToBinary(String hexString)
    {
        // hexString的长度对2取整，作为bytes的长度
        int len = hexString.length() / 2;
        byte[] bytes = new byte[len];
        byte high = 0;// 字节高四位
        byte low = 0;// 字节低四位
        
        for (int i = 0; i < len; i++)
        {
            // 右移四位得到高位
            high = (byte)((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
            low = (byte)hexStr.indexOf(hexString.charAt(2 * i + 1));
            bytes[i] = (byte)(high | low);// 高地位做或运算
        }
        return bytes;
    }     
   
    public static void main(String[] args) {

        
        String a[] = new String[]{
        		"E09D2CC71B4E12279305D6F0359B2DEA"
        		};
        for(int i = 0;i<a.length;i++){
        	System.out.println(decrypt(a[i]));
        }
        
    }
    
}
