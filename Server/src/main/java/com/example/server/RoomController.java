package com.example.server;

import Database.DatabaseConnectionDelete;
import Database.DatabaseConnectionInsert;
import Objekte.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Optional;

@RestController
public class RoomController {
    DatabaseConnectionInsert insert = new DatabaseConnectionInsert();
    DatabaseConnectionDelete delete = new DatabaseConnectionDelete();
    public static LinkedList<Room> rooms = new LinkedList<>();

    public RoomController() throws SQLException {
    }

    @PostMapping("/rooms")
    public void CreateRoom (@RequestParam Optional<String> name,
                            @RequestParam String size,
                            @RequestParam String id,
                            @RequestParam String windows,
                            @RequestParam String fans,
                            @RequestParam String doors,
                            @RequestParam String lights
                            ) {
        /*
        Room room = new Room(name, size, 1);
        DatabaseConnectionInsert(room);
        int numOfDoors;
try {
    numOfDoors = Integer.parseInt(NumOfDoors.getText());
} catch (NumberFormatException e) {
    // Handle the exception here, for example by showing an error message to the user
    return;
}


         */




        String name2 = name.get();

        System.out.println("Name: " + name2);



        int size2 = Integer.parseInt(size);
        int id2 = Integer.parseInt(id);
        int windows2 = Integer.parseInt(windows);
        int fans2 = Integer.parseInt(fans);
        int doors2 = Integer.parseInt(doors);
        int lights2 = Integer.parseInt(lights);




        LinkedList<Thing> things = new LinkedList<>();

        try{
            int numOfD = doors2;
            if (numOfD < 0) {
                throw new InputMismatchException();
            }else{
                int doorId = insert.doorDataID();
                for (int i = 0; i < numOfD; i++) {
                    Door door = new Door("Door");
                    door.setDoorId(doorId);
                    things.add(door);
                    doorId++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }

        try{
            int numOfF = fans2;
            if (numOfF < 0) {
                throw new Exception();
            }else{
                int fanId = insert.fanDataID();
                for (int i = 0; i < numOfF; i++) {
                    Fan fan = new Fan("Fan");
                    fan.setFanId(fanId);
                    things.add(fan);
                    fanId++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }

        try{
            int numOfL = lights2;
            if(numOfL < 0){
                throw new Exception();
            }else{
                int lightId = insert.lightDataID();
                for (int i = 0; i < numOfL; i++) {
                    Light light = new Light("Light");
                    light.setLightId(lightId);
                    things.add(light);
                    lightId++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }

        try{
            int numOfW = windows2;
            if(numOfW < 0){
                throw new Exception();
            }else{
                int windowId = insert.windowDataID();
                for (int i = 0; i < numOfW; i++) {
                    Window window = new Window("Window");
                    window.setWindowId(windowId);
                    things.add(window);
                    windowId++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }

        try{
            if(size2<0){
                throw new Exception();
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Only Integers!");
            return;
        }


        boolean x = true;
        for(Room room: rooms){
            if (room.getId() ==  id2){
                x = false;
                break;
            }
        }

        if(x){
            Room room = new Room(name2, size2, things, id2);
            insert.insertRoom(room);
            rooms.add(room);
            //DatabaseConnection.AddRoomToDatabase(room);

        }else{
            System.out.println("Room ID already exists");
        }




    }





}





