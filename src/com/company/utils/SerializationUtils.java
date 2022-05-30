package com.company.utils;

import java.io.*;

public class SerializationUtils {
    /**
     * Serialize the given object to the file
     */
    public static void serialize(Object obj, String fileName) {
        try(FileOutputStream fos = new FileOutputStream(fileName, true);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Cannot serialize file: %s, cause %s%n\n", fileName, e.getCause());
        }
    }

    /**
     * Deserialize to an object from the file
     */
    public static Object deserialize(String fileName) {
        var file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("Cannot deserialize file: %s, cause %s%n\n", fileName, e.getMessage());
        }
        return null;
    }
}
