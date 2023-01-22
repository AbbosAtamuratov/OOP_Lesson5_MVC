package personal.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class FileOperationParent {

    protected String fileName;

    public FileOperationParent() {
        this.fileName = runPathway();
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private String runPathway() {
        Scanner sc = new Scanner(System.in);
        System.out.println("C каким типом файлов будем работать?\n" +
                "1. TXT\n" +
                "2. XML\n");
        String choice = sc.nextLine();
        if (Integer.parseInt(choice) == 1) {
            return "users.txt";
        } else
            return "users.xml";
    }

}