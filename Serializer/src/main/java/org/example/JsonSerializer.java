package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.List;

public class JsonSerializer {

    public static void serializeToJsonFile(List<Stalker> stalkers, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), stalkers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        List<Stalker> stalkers = ObjectGenerator.generateStalkerList(1000000);
        System.out.println("Object list was created");

        JsonSerializer.serializeToJsonFile(stalkers, "stalkers.json");
    }
}
