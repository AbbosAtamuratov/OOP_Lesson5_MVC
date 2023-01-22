package personal.model;

import java.util.ArrayList;
import java.util.List;

public class UserMapperXml implements UserMapper {
    @Override
    public String map (User user){
        return String.format("<User>\n" +
                "<Id>%s</Id>\n" +
                "<Name>%s</Name>\n" +
                "<LastName>%s</LastName>\n" +
                "<Phone>%s</Phone>\n" +
                "</User>\n", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }
    @Override
    public User map (String line){
        System.out.println(line);
        String[] lines = line.split("\n");
        List<String> protoUser = new ArrayList<>();
        for (int i =1; i<lines.length-1; i++) {
            System.out.println(lines[i]);
            int start = lines[i].charAt('>') + 1;
            int end = lines[i].charAt('/') - 2;
            protoUser.add(lines[i].substring(start,end));
        }
        System.out.println(protoUser.toString());
        return new User(protoUser.get(0), protoUser.get(1), protoUser.get(2), protoUser.get(3));
    }
}
