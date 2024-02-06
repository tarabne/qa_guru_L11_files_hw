package ru.tarabne;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FilesParsingTests {
    private final ClassLoader cl = FilesParsingTests.class.getClassLoader();

    @Test
    void csvInZipParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("file.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("color_srgb.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(
                            new String[] {"Aqua","#00FFFF", "rgb(0,100,100)"}, content.get(11)
                    );
                }
                System.out.println(entry.getName());
            }
        }
    }

    @Test
    void pdfInZipParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("file.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("magic.pdf")) {
                    PDF content = new PDF(zis);
                    Assertions.assertTrue(content.text.contains("The smiley face in the previous section"));
                }
            }
        }
    }

    @Test
    void xlsxInZipParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("file.zip");
        ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("sample2.xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals("2016.1.1",xls.excel.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue());
                }
            }
        }
    }

}
