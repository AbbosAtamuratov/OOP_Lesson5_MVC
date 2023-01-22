package personal.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperationTXT extends FileOperationParent implements FileOperation {

    private String fileName;

    public FileOperationTXT() {
        super();
        }


    public List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                // считываем остальные строки в цикле
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void saveAllLines(List<String> lines) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (String line : lines) {
                // запись всей строки
                writer.write(line);
                // запись по символам
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public String getFileType() { return fileName.substring(fileName.length()-3);}
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