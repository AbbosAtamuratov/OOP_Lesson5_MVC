package personal.model;

public interface Mapper {
    User map(String line);
    String map(User user);
}
