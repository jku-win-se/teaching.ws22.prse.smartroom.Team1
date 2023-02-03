Drop Table if exists "ROOM" Cascade;
Drop Table if exists "GLASSWINDOW" Cascade;
Drop Table if exists "DOOR" Cascade;
Drop Table if exists "FAN" Cascade;
Drop Table if exists "DOORCONNECTION" Cascade;
DROP TABLE if exists "LIGHT" Cascade;

DROP TABLE if exists "GLASSWINDOW_LOGGING" Cascade;
DROP TABLE if exists "DOOR_LOGGING" Cascade;
DROP TABLE if exists "FAN_LOGGING" Cascade;
DROP TABLE if exists "LIGHT_LOGGING" Cascade;
DROP TABLE if exists "ROOM_TEMPERATURE_LOGGING" Cascade;
DROP TABLE if exists "ROOM_PEOPLE_LOGGING" Cascade;
DROP TABLE if exists "ROOM_CO2_LOGGING" Cascade;




CREATE TABLE "ROOM" (
                        "roomID" int PRIMARY KEY NOT NULL,
                        "roomSize" int NOT NULL,
                        "roomName" VARCHAR NOT NULL
);


CREATE TABLE "ROOM_TEMPERATURE_LOGGING"(
                                           "timeStamp" timestamp NOT NULL,
                                           "temperature" int NOT NULL,
                                           "roomID" int NOT NULL,
                                           PRIMARY KEY ("timeStamp","temperature", "roomID" )
);

CREATE TABLE "ROOM_PEOPLE_LOGGING"(
                                      "timeStamp" timestamp NOT NULL,
                                      "numberOfPeople" int NOT NULL,
                                      "roomID" int NOT NULL,
                                      PRIMARY KEY ("timeStamp","numberOfPeople", "roomID" )
);

CREATE TABLE "ROOM_CO2_LOGGING"(
                                      "timeStamp" timestamp NOT NULL,
                                      "co2" int NOT NULL,
                                      "roomID" int NOT NULL,
                                      PRIMARY KEY ("timeStamp","co2", "roomID" )
);

CREATE TABLE "GLASSWINDOW" (
                               "windowID" int PRIMARY KEY NOT NULL,
                               "roomID" int NOT NULL
);


CREATE TABLE "DOOR" (
                    "doorID" int PRIMARY KEY NOT NULL,
                    "roomID" int NOT NULL

);


CREATE TABLE "FAN" (
                       "fanID" int PRIMARY KEY NOT NULL,
                       "roomID" int NOT NULL
);

CREATE TABLE "DOORCONNECTION" (
                                  "doorID" int NOT NULL,
                                  "roomID" int NOT NULL,
                                  PRIMARY KEY ("doorID", "roomID")
);

CREATE TABLE "LIGHT" (
                         "lightID" int PRIMARY KEY  NOT NULL,
                         "roomID" INT NOT NULL
);






CREATE TABLE "GLASSWINDOW_LOGGING" (
                                       "timeStamp" timestamp NOT NULL,
                                       "switch" boolean NOT NULL,
                                       "windowID" int NOT NULL,
                                       PRIMARY KEY ("timeStamp","switch", "windowID" )
);

CREATE TABLE "DOOR_LOGGING" (
                                "timeStamp" timestamp NOT NULL,
                                "switch" boolean NOT NULL,
                                "doorID" int NOT NULL,
                                PRIMARY KEY ("timeStamp","switch", "doorID" )
);

CREATE TABLE "FAN_LOGGING" (
                               "timeStamp" timestamp NOT NULL,
                               "switch" boolean NOT NULL,
                               "fanID" int NOT NULL,
                               PRIMARY KEY ("timeStamp","switch", "fanID" )
);

CREATE TABLE "LIGHT_LOGGING" (
                                 "timeStamp" timestamp NOT NULL,
                                 "switch" boolean NOT NULL,
                                 "lightID" int NOT NULL,
                                 PRIMARY KEY ("timeStamp","switch", "lightID" )
);

ALTER TABLE "GLASSWINDOW" ADD FOREIGN KEY ("roomID") REFERENCES "ROOM" ("roomID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "FAN" ADD FOREIGN KEY ("roomID") REFERENCES "ROOM" ("roomID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "DOORCONNECTION" ADD FOREIGN KEY ("doorID") REFERENCES "DOOR" ("doorID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "DOORCONNECTION" ADD FOREIGN KEY ("roomID") REFERENCES "ROOM" ("roomID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "LIGHT" ADD FOREIGN KEY ("roomID") REFERENCES "ROOM" ("roomID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "DOOR" ADD FOREIGN KEY ("roomID") REFERENCES "ROOM" ("roomID") ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE "GLASSWINDOW_LOGGING" ADD FOREIGN KEY ("windowID") REFERENCES "GLASSWINDOW" ("windowID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "DOOR_LOGGING" ADD FOREIGN KEY ("doorID") REFERENCES "DOOR" ("doorID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "FAN_LOGGING" ADD FOREIGN KEY ("fanID") REFERENCES "FAN" ("fanID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "LIGHT_LOGGING" ADD FOREIGN KEY ("lightID") REFERENCES "LIGHT" ("lightID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "ROOM_TEMPERATURE_LOGGING" ADD FOREIGN KEY ("roomID") REFERENCES "ROOM" ("roomID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "ROOM_PEOPLE_LOGGING" ADD FOREIGN KEY ("roomID") REFERENCES "ROOM" ("roomID") ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "ROOM_CO2_LOGGING" ADD FOREIGN KEY ("roomID") REFERENCES "ROOM" ("roomID") ON UPDATE CASCADE ON DELETE CASCADE;
