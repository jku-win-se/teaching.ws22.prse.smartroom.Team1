package com.example.smartroom_javafx;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

public class DatabaseConnectionImport {

    static String username = "postgres";
    static String password = "user123";
    static String URL = "jdbc:postgresql://localhost/postgres";
    static Connection connection;

    public static class ImportData {
        private Connection connection;
        private final String SQL_INSERT_ROOM = "INSERT INTO public.\"ROOM\"(\n" +
                "\t\"roomName\", \"roomSize\", \"roomID\")\n" +
                "\tVALUES (?, ?, ?);";
        private final String SQL_INSERT_VENTILATOR = "INSERT INTO public.\"FAN\"(\n" +
                "\t\"fanID\", \"roomID\")\n" +
                "\tVALUES (?, ?);";
        private final String SQL_INSERT_DOOR = "INSERT INTO public.\"DOOR\"(\n" +
                "\t\"doorID\")\n" +
                "\tVALUES (?);";
        private final String SQL_INSERT_WINDOW = "INSERT INTO public.\"GLASSWINDOW\"(\n" +
                "\t\"windowID\", \"roomID\")\n" +
                "\tVALUES (?, ?);";

        public ImportData(File file) throws SQLException {
            try {
                Workbook workbook = new XSSFWorkbook(file);
                Iterator<Sheet> sheetIterator = workbook.sheetIterator();
                connection = DriverManager.getConnection(URL, username, password);
                System.out.println("Connected to the PostgreSQL server successfully.");
                System.out.println(" ");

                while (sheetIterator.hasNext()) {
                    Sheet sh = sheetIterator.next();
                    System.out.println(sh.getSheetName());
                    System.out.println("-------------------");
                    int rows = sh.getLastRowNum();
                    if (sh.getSheetName().equals("Room")) {
                        insertData(SQL_INSERT_ROOM, rows, sh);
                    }
                    if (sh.getSheetName().equals("Ventilator")) {
                        insertData(SQL_INSERT_VENTILATOR, rows, sh);
                    }
                    if (sh.getSheetName().equals("Door")) {
                        insertData(SQL_INSERT_DOOR, rows, sh);
                    }
                    if (sh.getSheetName().equals("Window")) {
                        insertData(SQL_INSERT_WINDOW, rows, sh);
                    }
                }
                connection.close();
            } catch (IOException | InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        private void insertData(String sql, int rows, Sheet sh) throws SQLException {
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                for (int r = 1; r <= rows; r++) { //we do not need the header
                    Row row = sh.getRow(r);
                    if (sql.equals(SQL_INSERT_ROOM)) {
                        pstmt.setString(1, row.getCell(0).getStringCellValue());
                        pstmt.setInt(2, (int) row.getCell(1).getNumericCellValue());
                    } else if (sql.equals(SQL_INSERT_VENTILATOR)) {
                        pstmt.setInt(1, (int) row.getCell(0).getNumericCellValue());
                        pstmt.setInt(2, (int) row.getCell(1).getNumericCellValue());
                    } else if (sql.equals(SQL_INSERT_DOOR)) {
                        pstmt.setInt(1, (int) row.getCell(0).getNumericCellValue());
                    } else if (sql.equals(SQL_INSERT_WINDOW)) {
                        pstmt.setInt(1, (int) row.getCell(0).getNumericCellValue());
                        pstmt.setInt(2, (int) row.getCell(1).getNumericCellValue());
                    }
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                System.out.println("Rows inserted: " + rows);
            }
        }
    }
}