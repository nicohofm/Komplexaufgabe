package cryptography.sha;

import Interfaces.IEncryption;
import org.apache.commons.codec.digest.DigestUtils;

public class SHAEncryption implements IEncryption {
    public String encrypt(String data)
    {
        return new DigestUtils("SHA3-256").digestAsHex(data);
    }

    @Override
    public String decrypt(String content) {
        return null;
    }
}
