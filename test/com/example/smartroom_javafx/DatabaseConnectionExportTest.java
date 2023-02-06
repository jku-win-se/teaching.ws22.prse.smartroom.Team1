package com.example.smartroom_javafx;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.example.smartroom_javafx.Database.DatabaseConnectionExport;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class DatabaseConnectionExportTest {

    @Test
    public void testExcelFileCreation() throws IOException, SQLException {
        //arrange + act
        DatabaseConnectionExport connection = new DatabaseConnectionExport();
        File file = new File(".\\SmartRoomExportTEST.xlsx");

        //assert
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void testExcelFileContent() throws IOException, SQLException, InvalidFormatException {

        //arrange + act
        DatabaseConnectionExport connection = new DatabaseConnectionExport();
        File file = new File(".\\SmartRoomExportTEST.xlsx");
        Workbook workbook = new XSSFWorkbook(file);

        //assert
        assertTrue(workbook.getNumberOfSheets() == 5);
        assertTrue(workbook.getSheet("ROOM").getRow(0).getCell(0).getStringCellValue().equals("roomID"));
        file.delete();
    }
}

