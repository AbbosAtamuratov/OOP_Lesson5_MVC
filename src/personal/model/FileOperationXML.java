package personal.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FileOperationXML implements FileOperation{

    private String fileName;


//    public void saveAllLines(List<User> users, String filePath) {
//        StringBuilder xmlTemplate = new StringBuilder();
//        xmlTemplate.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
//        xmlTemplate.append("<Phonebook>\n");
//        users.forEach(i -> xmlTemplate.append(""));
//        xmlTemplate.append("</Phonebook>");
//        System.out.println(xmlTemplate.toString());
//    }

    @Override
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

    @Override
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

    }


