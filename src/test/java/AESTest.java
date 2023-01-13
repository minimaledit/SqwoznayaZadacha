import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AESTest {
    @Test
    public void testEncryption() throws Exception {
        byte[] plainText = "Hello World".getBytes();
        byte[] key = "cRfUjXnZr4u7x!A%D*G-KaPdSgVkYp3s".getBytes();
        byte[] cipherText = AES.encrypt(plainText, key);
        assertNotEquals(plainText, cipherText);
        byte[] decryptedText = AES.decrypt(cipherText, key);
        assertArrayEquals(plainText, decryptedText);
    }

    @Test
    public void testDecryption() throws Exception {
        byte[] plainText = "Hello World".getBytes();
        byte[] key = "cRfUjXnZr4u7x!A%D*G-KaPdSgVkYp3s".getBytes();
        byte[] cipherText = AES.encrypt(plainText, key);
        byte[] decryptedText = AES.decrypt(cipherText, key);
        assertArrayEquals(plainText, decryptedText);
    }
}