package com.epam.mjc.io;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String str = "";
        try (FileInputStream stream = new FileInputStream(file)) {
            int symbol;
            while ((symbol = stream.read()) != -1) {
                str += (char) symbol;
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(FileReader.class.getName());
            logger.log(Level.ALL, "Error while reading the file,");
        }

        Map<String, String> m = new HashMap<>();
        String[] pairs = str.split("\n");
        for (String item : pairs) {
            String[] keyValue = item.split(": ");
            m.put(keyValue[0], keyValue[1]);
        }
        String name = m.get("Name");
        int age = Integer.parseInt(m.get("Age"));
        String email = m.get("Email");
        long phone = Long.parseLong(m.get("Phone"));


        return new Profile(name, age, email, phone);
    }
}
