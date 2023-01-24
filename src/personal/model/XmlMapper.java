package personal.model;

import java.util.HashMap;

public class XmlMapper implements Mapper{
    @Override
    public User map(String line) {
        HashMap<String, String> user = new HashMap<>();
        String[] lines = line.split("\n");
        user.put("id", lines[0].substring(lines[0].indexOf('\"')+1,lines[0].indexOf("\">")));
        user.put("firstName", lines[1].substring(lines[1].indexOf("me>")+3, lines[1].indexOf("</")));
        user.put("lastName", lines[2].substring(lines[2].indexOf("me>")+3, lines[2].indexOf("</")));
        user.put("phone", lines[3].substring(lines[3].indexOf("ne>")+3, lines[3].indexOf("/p")-1));
        return new User(user);
    }

    @Override
    public String map(User user) {
        return String.format(
                "<user id=\"%s\">\n" +
                "<firstName>%s</firstName>\n" +
                "<lastName>%s</lastName>\n" +
                "<phone>%s</phone>\n" +
                "</user>\n", user.getId(), user.getFirstName(),user.getLastName(), user.getPhone());
    }
}
