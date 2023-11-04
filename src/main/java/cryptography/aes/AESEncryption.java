package cryptography.aes;

import Interfaces.IEncryption;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

public class AESEncryption implements IEncryption {

    public AlgorithmParameterSpec buildIV() {
        return new IvParameterSpec(Configuration.INSTANCE.iv.getBytes(StandardCharsets.UTF_8));
    }

    public Key buildKey() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] key = messageDigest.digest(Configuration.INSTANCE.key.getBytes(StandardCharsets.UTF_8));
            return new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException nse) {
            System.out.println(nse.getMessage());
        }

        return null;
    }

    public String encrypt(String plainMessage) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, buildKey(), buildIV());
            return new String(new Base64().encode(cipher.doFinal(plainMessage.getBytes())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void encryptFile(String path, String finalFilename)
    {
        try
        {
            String content = Files.readString(Paths.get(path));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, buildKey(), buildIV());
            FileOutputStream fileOut = new FileOutputStream(finalFilename);
            CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher);
            fileOut.write(cipher.getIV());
            cipherOut.write(content.getBytes());
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }


    }

    public String decrypt(String encryptedMessage) {
        String decryptedMessage;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, buildKey(), buildIV());
            decryptedMessage = new String(cipher.doFinal(new Base64().decode(encryptedMessage)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return decryptedMessage;
    }
}
