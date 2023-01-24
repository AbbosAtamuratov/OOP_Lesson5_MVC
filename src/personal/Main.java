package personal;

import personal.controllers.UserController;
import personal.model.*;
import personal.views.ViewUser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileManager();
        if (((FileManager) fileOperation).getFileName().equals("unchosen"))
            return;
        Repository repository = new RepositoryFile(fileOperation);
        UserController controller = new UserController(repository);
        ViewUser view = new ViewUser(controller);
        view.run();

    }
}
