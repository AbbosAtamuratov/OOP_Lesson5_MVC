package personal;

import personal.controllers.UserController;
import personal.model.FileOperation;
import personal.model.FileManager;
import personal.model.Repository;
import personal.model.RepositoryFile;
import personal.views.ViewUser;

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
