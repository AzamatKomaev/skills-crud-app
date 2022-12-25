package com.azamat_komaev.crudapp.view;

import com.azamat_komaev.crudapp.controller.DeveloperController;
import com.azamat_komaev.crudapp.model.Developer;
import com.azamat_komaev.crudapp.util.ViewUtil;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DeveloperView implements GenericView {
    private final DeveloperController controller;
    private final Scanner scanner;

    public DeveloperView() {
        this.controller = new DeveloperController();
        this.scanner = new Scanner(System.in);
    }

    private void printAllDevelopers() {
        System.out.println(this.controller.getAll());
    }

    private void printOneDeveloper() {
        System.out.print("Enter developer id should be gotten: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());
        Developer developerToPrint;

        try {
            developerToPrint = this.controller.getOne(id);
            System.out.println(developerToPrint);
        } catch (NoSuchElementException e) {
            System.out.println("There is no any developer with such id!");
        }

    }

    private void saveAndPrintDeveloper() {
        String[] firstName = new String[1];
        String[] lastName = new String[1];
        Integer[] specialtyId = new Integer[1];
        List<Integer> skillsIds = new ArrayList<>();

        ViewUtil.readAndParseDeveloperFields(firstName, lastName, specialtyId, skillsIds);
        Developer newDeveloper = this.controller.save(firstName[0], lastName[0], skillsIds, specialtyId[0]);
        System.out.println(newDeveloper);
    }

    private void updateAndPrintDeveloper() {
        System.out.print("Enter developer id should be updated: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());
        String[] firstName = new String[1];
        String[] lastName = new String[1];
        Integer[] specialtyId = new Integer[1];
        List<Integer> skillsIds = new ArrayList<>();

        ViewUtil.readAndParseDeveloperFields(firstName, lastName, specialtyId, skillsIds);

        try {
            Developer developerToPrint = this.controller.update(id, firstName[0], lastName[0], skillsIds, specialtyId[0]);
            System.out.println(developerToPrint);
        } catch (NoSuchElementException e) {
            System.out.println("There is no any developer with such id!");
        }
    }

    private void deleteDeveloperAndPrintWasOperationSuccessful() {
        System.out.print("Enter developer id should be deleted: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());

        try {
            this.controller.destroy(id);
            System.out.println("The developer was deleted successful!");
        } catch (NoSuchElementException e) {
            System.out.println("There is no any developer with such id!");
        }

    }

    @Override
    public void runCommand(String command) {
        switch (command) {
            case "get_all" -> printAllDevelopers();
            case "get_one" -> printOneDeveloper();
            case "create" -> saveAndPrintDeveloper();
            case "update" -> updateAndPrintDeveloper();
            case "delete" -> deleteDeveloperAndPrintWasOperationSuccessful();
            default -> System.out.println("Invalid command, try again!");
        }
    }
}
