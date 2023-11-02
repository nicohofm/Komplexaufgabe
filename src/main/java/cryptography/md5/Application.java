package cryptography.md5;

import org.apache.commons.codec.digest.DigestUtils;

public class Application {
    public static void main(String... args) {
        String plainMessage = "dhbw2023!";
        String encryptedMessage = new DigestUtils("MD5").digestAsHex(plainMessage);

        System.out.println("plainMessage     : " + plainMessage);
        System.out.println("encryptedMessage : " + encryptedMessage);
    }
}