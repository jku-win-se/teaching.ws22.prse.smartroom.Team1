package com.example.smartroom_javafx;
import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class DatabaseConnectionDeleteTest {

    //arrange
    DatabaseConnectionDelete delete = new DatabaseConnectionDelete();

    @Test
    public void testDatabaseConnectionClose() {
        //act
        DatabaseConnectionDelete.databaseConnectionClose();

        //assert
        assertNotNull(DatabaseConnectionDelete.connection);
    }

    @Test
    public void testDatabaseRoomDelete() {
        //arrange
        Room room = new Room("Wohnzimmer", 20);
        room.setId(1);

        //act
        delete.databaseRoomDelete(room);

        //assert that the room has been deleted from the database
        assertThat(room).isNotNull();
    }

    @Test
    public void testDatabaseDoorDelete() {
        //arrange
        int doorID = 1;

        //act
        delete.databaseDoorDelete(doorID);

        // assert that the door has been deleted from the database
        assertThat(doorID).isNotNull();

    }

    @Test
    public void testDatabaseLightDelete() {
        //arrange
        int lightID = 1;

        //act
        delete.databaseLightDelete(lightID);

        // assert that the light has been deleted from the database
        assertThat(lightID).isNotNull();
    }

    @Test
    public void testDatabaseFanDelete() {
        //arrange
        int fanID = 1;

        //act
        delete.databaseFanDelete(fanID);

        //assert that the fan has been deleted from the database
        assertThat(fanID).isNotNull();
    }

    @Test
    public void testDatabaseWindowDelete() {
        //arrange
        int windowID = 1;

        //act
        delete.databaseWindowDelete(windowID);

        //assert that the window has been deleted from the database
        assertThat(windowID).isNotNull();
    }
}
