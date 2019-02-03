/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstdbassignmenthashmap;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruger
 */
public class FirstDbAssignmentHashMap {

    private HashMap<String, String> hashList = new HashMap<>();
    private boolean isRunning = true;
    private Scanner sc = new Scanner(System.in);
    private int choice = 0;
    String fileName = "temp.bin";

    public static void main(String[] args) throws java.io.IOException {

        FirstDbAssignmentHashMap main = new FirstDbAssignmentHashMap();

        while (main.isRunning) {

            System.out.println("What would you like to do?\n1: make the map, 2: Print map, 3: Save the map, 4: Load the map");

            main.choice = main.sc.nextInt();

            switch (main.choice) {
                case 1:
                    System.out.println("How many entries should the map have?");
                    main.makeMap(main.sc.nextInt());
                    System.out.println("Map has been maked");
                    break;
                case 2:
                    System.out.println("Currently loaded map:\n" + (main.hashList.toString()) + "\n");
                    break;
                case 3:
                    main.writeTheFile();
                    break;
                case 4:
                    main.loadMap();
                    break;
                default:
                    System.out.println("Error, input has to be between 1 - 3.");
                    break;
            }

        }

    }

    public HashMap<String, String> makeMap(int amount) {

        for (int i = 0; i < amount; i++) {

            hashList.put("" + i, "hashmap");

        }
        return hashList;
    }

    public void loadMap() {

        try {

            byte[] buffer = new byte[1000];

            FileInputStream inputStream
                    = new FileInputStream(fileName);

            int total = 0;
            int nRead = 0;
            while ((nRead = inputStream.read(buffer)) != -1) {
               
                System.out.println(new String(buffer));
                total += nRead;
            }

            // Always close files.
            inputStream.close();

            System.out.println("Read " + total + " bytes");
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");

        }

    }

    public void writeTheFile() {
        byte[] buffer = null;
        int i = 0;
        try {
            FileOutputStream outputStream
                    = new FileOutputStream(fileName);

            for (String object : hashList.values()) {

                buffer = object.getBytes();
                outputStream.write(i);
                outputStream.write(buffer);
                i++;

            }

            outputStream.close();

            System.out.println("Wrote " + buffer.length
                    + " bytes");
        } catch (IOException ex) {
            System.out.println(
                    "Error writing file '"
                    + fileName + "'");

        }
    }
}
