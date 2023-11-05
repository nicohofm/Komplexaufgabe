import Classes.TraficSpikes;
import Classes.Wallet;
import cryptography.aes.AESEncryption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestApplication {
    @Test
    public void testIsOwnerWanted() {
        AESEncryption aesEncryption = new AESEncryption();

        String face = "CAFFCFFAFCFEFAE";
        String expectedEncryptedFace = aesEncryption.encrypt(face);

        String encryptedFace = aesEncryption.encrypt(face);
        assertEquals(expectedEncryptedFace, encryptedFace);
    }

    @Test
    public void testCheckLicensePlate() {
        AESEncryption aesEncryption = new AESEncryption();

        String licensePlate = "HD5GHV";
        String dataCarOwner = aesEncryption.encrypt(licensePlate);
        String expectedDataCarOwner = aesEncryption.decrypt(dataCarOwner);

        assertEquals(expectedDataCarOwner, licensePlate);
    }

    @Test
    public void testLaunchMethodIsCalled() {
        TraficSpikes traficSpikes = mock(TraficSpikes.class);
        traficSpikes.launch();
        verify(traficSpikes, times(1)).launch();
    }

    @Test
    public void testGetMoney() {
        Wallet wallet = new Wallet(100); // Annahme: 100 Euro auf dem Konto

        double amountToWithdraw = 50.0; // Betrag zum Abheben
        wallet.getMoney(amountToWithdraw);

        double expectedDeposit = 100.0 - amountToWithdraw;
        assertEquals(expectedDeposit, wallet.getDeposit(), 0.01); // Toleranz von 0.01 für Gleitkommavergleiche
    }

    @Test
    public void testGetDeposit() {
        double money = 100.0;
        Wallet wallet = new Wallet(money);

        double deposit = wallet.getDeposit();
        assertEquals(money, deposit);
    }
}