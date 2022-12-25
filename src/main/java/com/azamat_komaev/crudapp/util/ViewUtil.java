package com.azamat_komaev.crudapp.util;

import com.azamat_komaev.crudapp.model.Developer;
import com.azamat_komaev.crudapp.view.DeveloperView;
import com.azamat_komaev.crudapp.view.GenericView;
import com.azamat_komaev.crudapp.view.SkillView;
import com.azamat_komaev.crudapp.view.SpecialtyView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public static List<Integer> readAndParseIntegerList() {
        Scanner scanner = new Scanner(System.in);
        String[] skillsIdsString = scanner.nextLine().split(" ");
        return Arrays.stream(skillsIdsString)
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();
    }

    public static void readAndParseDeveloperFields(String[] firstName, String[] lastName,
                                                   Integer[] specialtyId, List<Integer> skillsIds) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter firstName: ");
        firstName[0] = scanner.nextLine();
        System.out.print("Enter lastName: ");
        lastName[0] = scanner.nextLine();
        System.out.print("Enter specialty id: ");
        specialtyId[0] = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter list of skill ids seperated with spaces: ");
        List<Integer> parsedSkillsIds = ViewUtil.readAndParseIntegerList();
        skillsIds.addAll(parsedSkillsIds);
    }
}
