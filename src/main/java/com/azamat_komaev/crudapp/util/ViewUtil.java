package com.azamat_komaev.crudapp.util;

import com.azamat_komaev.crudapp.view.GenericView;
import com.azamat_komaev.crudapp.view.SkillView;

import java.util.Scanner;

public class ViewUtil {
    public static String askForEntityName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter entity name you want to interact with: ");
        return scanner.nextLine();
    }

    public static GenericView getViewModelByEntityName(String entityName) {
        switch (entityName) {
            case "skill":
                return new SkillView();
            default:
                System.out.println("Invalid entity name!");
                return null;
        }
    }

    public static void askForCommandAndRunIt(GenericView view) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter commmand: ");
        String command = scanner.nextLine();
        view.runCommand(command);
    }
}
