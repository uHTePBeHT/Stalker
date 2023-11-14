package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFileDeserializer {

    public static List<Stalker> stalkersD;


    public static List<Stalker> deserializeObjects(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            List<Stalker> stalkersD = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Stalker.class));
            return stalkersD;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Stalker> getStalkersD() {
        return stalkersD;
    }

    public static void main(String[] args) {
        stalkersD = JsonFileDeserializer.deserializeObjects("D:\\Java Projects\\Stalker\\stalkers.json");

        if (stalkersD != null) {
            System.out.println("Deserialization success");
            for (Stalker stalker : stalkersD) {
                System.out.println("ID: " + stalker.getId() + " Name: " + stalker.getFirstName() + " Surname: " + stalker.getSecondName()
                + " Group: " + stalker.getGroup());
            }
        }
        System.out.println(stalkersD);
    }
}
