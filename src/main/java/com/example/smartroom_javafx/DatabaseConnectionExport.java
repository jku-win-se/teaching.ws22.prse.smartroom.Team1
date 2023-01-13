package com.example.smartroom_javafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.stream.IntStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class DatabaseConnectionExport {

    static String username = "postgres";
    static String password = "user123";
    static String URL = "jdbc:postgresql://localhost/postgres";
    static Connection connection;

    public DatabaseConnectionExport() throws SQLException, IOException {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            Statement statm = connection.createStatement();


            //-----ROOM
            ResultSet result = statm.executeQuery("Select * From public.\"ROOM\"");
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheetRoom = wb.createSheet("ROOM");
            String[] columnOfRoom = {"roomID", "roomSize", "roomName"};
            XSSFRow rowOfRoom = sheetRoom.createRow(0);
            IntStream.range(0, columnOfRoom.length).forEach(i -> {
                Cell cell = rowOfRoom.createCell(i);
                cell.setCellValue(columnOfRoom[i]);
            });
            int i = 1;
            if (result.next()) {
                do {
                    XSSFRow row = sheetRoom.createRow(i);
                    row.createCell(0).setCellValue((result.getInt("roomID")));
                    row.createCell(1).setCellValue(result.getInt("roomSize"));
                    row.createCell(2).setCellValue(result.getString("roomName"));
                    i++;
                } while (result.next());
            }
            IntStream.range(0, columnOfRoom.length - 1).forEach(sheetRoom::autoSizeColumn);


            //-----DOOR
            result = statm.executeQuery("Select * From public.\"DOOR\"");
            XSSFSheet sheetDoor = wb.createSheet("DOOR");
            String[] columnOfDoor = {"doorID", "roomID"};
            XSSFRow rowOfDoor = sheetDoor.createRow(0);
            IntStream.range(0, columnOfDoor.length).forEach(k -> {
                Cell cell = rowOfDoor.createCell(k);
                cell.setCellValue(columnOfDoor[k]);
            });
            i = 1;
            if (result.next()) {
                do {
                    XSSFRow row = sheetDoor.createRow(i);
                    row.createCell(0).setCellValue((result.getInt("doorID")));
                    row.createCell(1).setCellValue(result.getInt("roomID"));
                    i++;
                } while (result.next());
            }


            //-----GLASSWINDOW
            result = statm.executeQuery("Select * From public.\"GLASSWINDOW\"");
            XSSFSheet sheetGlasswindow = wb.createSheet("GLASSWINDOW");
            String[] columnOfGlasswindow = {"windowID", "roomID"};
            XSSFRow rowOfGlasswindow = sheetGlasswindow.createRow(0);
            IntStream.range(0, columnOfGlasswindow.length).forEach(k -> {
                Cell cell = rowOfGlasswindow.createCell(k);
                cell.setCellValue(columnOfGlasswindow[k]);
            });
            i = 1;
            if (result.next()) {
                do {
                    XSSFRow row = sheetGlasswindow.createRow(i);
                    row.createCell(0).setCellValue((result.getInt("windowID")));
                    row.createCell(1).setCellValue(result.getInt("roomID"));
                    i++;
                } while (result.next());
            }


            //-----FAN
            result = statm.executeQuery("Select * From public.\"FAN\"");
            XSSFSheet sheetFan = wb.createSheet("FAN");
            String[] columnOfFan = {"fanID", "roomID"};
            XSSFRow rowOfFan = sheetFan.createRow(0);
            IntStream.range(0, columnOfFan.length).forEach(k -> {
                Cell cell = rowOfFan.createCell(k);
                cell.setCellValue(columnOfFan[k]);
            });
            i = 1;
            if (result.next()) {
                do {
                    XSSFRow row = sheetFan.createRow(i);
                    row.createCell(0).setCellValue((result.getInt("fanID")));
                    row.createCell(1).setCellValue(result.getInt("roomID"));
                    i++;
                } while (result.next());
            }


            //-----LIGHT
            result = statm.executeQuery("Select * From public.\"LIGHT\"");
            XSSFSheet sheetLight = wb.createSheet("LIGHT");
            String[] columnOfLight = {"lightID", "roomID"};
            XSSFRow rowOfLight = sheetLight.createRow(0);
            IntStream.range(0, columnOfLight.length).forEach(k -> {
                Cell cell = rowOfLight.createCell(k);
                cell.setCellValue(columnOfLight[k]);
            });
            i = 1;
            if (result.next()) {
                do {
                    XSSFRow row = sheetLight.createRow(i);
                    row.createCell(0).setCellValue((result.getInt("lightID")));
                    row.createCell(1).setCellValue(result.getInt("roomID"));
                    i++;
                } while (result.next());
            }

            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\gabho\\OneDrive\\Bloatware\\Desktop\\Export\\SmartRoomExportTEST.xlsx");
            //cannot save in "C:"
            wb.write(outputStream);
            outputStream.close();
            System.out.println("Export Success!");
            wb.close();
            statm.close();
        }

        catch(SQLException | FileNotFoundException e){
        }
        catch (IOException e){
            e.printStackTrace();

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
