package personal.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager implements FileOperation {

    private String fileName;

    public FileManager() {
        this.fileName = formatChooser();
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
                if (fileName.contains("xml")) {
                    lines.remove(0);
                    lines.remove(0);
                    lines.remove(lines.size()-1);
                    StringBuilder sb = new StringBuilder();
                    lines.forEach(i-> {
                        sb.append(i);
                        sb.append("\n");
                    });
                    sb.deleteCharAt(0);
                    sb.deleteCharAt(sb.length()-1);
                    lines.clear();
                    List<String> temp = List.of(sb.toString().split("user"));
                    temp.forEach(i->{
                        if (i.contains("id"))
                            lines.add(i);
                    });
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return lines;
    }

    public void saveAllLines(List<String> lines) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            if (fileName.contains("xml"))
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<users>\n");
            for (String line : lines) {
                // запись всей строки
                writer.write(line);
                // запись по символам
                writer.append('\n');
            }
            if (fileName.contains("xml"))
                writer.write("</users>");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getFileName() { return fileName; }

    private String formatChooser(){
        System.out.println("C каким фоматом будем работать?\n" +
                "1. txt\n" +
                "2. xml\n");
        Scanner scanner = new Scanner(System.in);
        String respond = scanner.nextLine();
        if (respond.toLowerCase().contains("txt") || respond.equals("1"))
            return "users.txt";
        else if (respond.toLowerCase().contains("xml") || respond.equals("2"))
            return "users.xml";
        else {
            System.out.println("Команда не распознана. До свидания...");
            return "unchosen";
        }
    }
}
