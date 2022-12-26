package com.azamat_komaev.crudapp.util;

import com.azamat_komaev.crudapp.view.DeveloperView;
import com.azamat_komaev.crudapp.view.GenericView;
import com.azamat_komaev.crudapp.view.SkillView;
import com.azamat_komaev.crudapp.view.SpecialtyView;

import java.util.Scanner;

public class ViewUtil {
    public static String askForAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter action: ");
        return scanner.nextLine();
    }

    public static String[] splitInputOnEntityNameAndCommand(String input) {
        return input.split("/");
    }

    public static GenericView getViewModelByEntityName(String entityName) {
        switch (entityName) {
            case "skill":
                return new SkillView();
            case "specialty":
                return new SpecialtyView();
            case "developer":
                return new DeveloperView();
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
