package cryptography.rsa;

import Interfaces.IEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAEncryption implements IEncryption {

    @Override
    public String encrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, loadPublicKey());
            byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(bytes));
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException |
                 NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String decrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, loadPrivateKey());
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(bytes);
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException |
                 NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private PrivateKey loadPrivateKey() {
        try {
            File privateKeyFile = new File("private.key");
            byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
            KeyFactory privateKeyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            return privateKeyFactory.generatePrivate(privateKeySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println(e);
        }

        return null;
    }

    private PublicKey loadPublicKey() {
        try {
            File publicKeyFile = new File("public.key");
            byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
            KeyFactory publicKeyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            return publicKeyFactory.generatePublic(publicKeySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
