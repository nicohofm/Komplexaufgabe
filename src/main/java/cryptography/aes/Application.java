package cryptography.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

public class Application {
    public static void main(String... args) {
        String plainMessage = "dhbw2023!";
        Application application = new Application();

        String encryptedMessage = application.encrypt(plainMessage);
        String decryptedMessage = application.decrypt(encryptedMessage);

        System.out.println("plainMessage      : " + plainMessage);
        System.out.println("encryptedMessage  : " + encryptedMessage);
        System.out.println("decryptedMessage  : " + decryptedMessage);
    }

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