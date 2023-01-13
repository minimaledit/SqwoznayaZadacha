import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ZipReaderGUITest {
    @Test
    public void testIsNumber() {
        assertTrue(ZipReaderGUI.isNumber('5'));
        assertTrue(ZipReaderGUI.isNumber('9'));
        assertFalse(ZipReaderGUI.isNumber('a'));
        assertFalse(ZipReaderGUI.isNumber('+'));
    }
    @Test
    public void testIsDec() {
        assertTrue(ZipReaderGUI.isDec('+'));
        assertTrue(ZipReaderGUI.isDec('/'));
        assertFalse(ZipReaderGUI.isDec('5'));
        assertFalse(ZipReaderGUI.isDec('a'));
    }
    @Test
    public void testMain() throws IOException {
        //creating test zip file
        File zipFile = new File("test.zip");
        byte[] buffer = new byte[1024];
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(fos);
        FileInputStream in = new FileInputStream("test.txt");
        zos.putNextEntry(new ZipEntry("test.txt"));
        int len;
        while ((len = in.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }
        zos.closeEntry();
        in.close();
        zos.close();
        fos.close();
        //creating test input
        String[] args = {"test.zip", "test.txt"};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        //call main method
        ZipReader.main(args);
        String output = baos.toString();
        //checking if output contains expected value
        assertTrue(output.contains("parsed expression:"));
        assertTrue(output.contains("result:"));
        //deleting test files
        zipFile.delete();
    }
}