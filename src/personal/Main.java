package personal;

import personal.controllers.UserController;
import personal.model.*;
import personal.views.ViewUser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationTXT();
        Repository repository = new RepositoryFile(fileOperation);
        UserController controller = new UserController(repository);
        ViewUser view = new ViewUser(controller);
        view.run();
    }

    private static String runPathway() {
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
