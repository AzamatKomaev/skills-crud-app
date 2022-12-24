package com.azamat_komaev.crudapp.view;

import com.azamat_komaev.crudapp.controller.SpecialtyController;
import com.azamat_komaev.crudapp.model.Specialty;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SpecialtyView implements GenericView {
    private final SpecialtyController controller;
    private final Scanner scanner;

    public SpecialtyView() {
        this.controller = new SpecialtyController();
        this.scanner = new Scanner(System.in);
    }

    private void printAllSpecialtys() {
        System.out.println(this.controller.getAll());
    }

    private void printOneSpecialty() {
        System.out.print("Enter specialty id should be gotten: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());
        Specialty specialtyToPrint;

        try {
            specialtyToPrint = this.controller.getOne(id);
            System.out.println(specialtyToPrint);
        } catch (NoSuchElementException e) {
            System.out.println("There is no any specialty with such id!");
        }

    }

    private void saveAndPrintSpecialty() {
        System.out.print("Enter name for new specialty: ");
        String name = this.scanner.nextLine();

        Specialty newSpecialty = this.controller.save(name);

        System.out.println(newSpecialty);
    }

    private void updateAndPrintSpecialty() {
        System.out.print("Enter specialty id should be updated: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());
        System.out.print("Enter new name for specialty you want to update: ");
        String name = this.scanner.nextLine();

        try {
            Specialty specialtyToPrint = this.controller.update(id, name);
            System.out.println(specialtyToPrint);
        } catch (NoSuchElementException e) {
            System.out.println("There is no any specialty with such id!");
        }

    }

    private void deleteSpecialtyAndPrintWasOperationSuccessful() {
        System.out.print("Enter specialty id should be deleted: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());

        try {
            this.controller.destroy(id);
            System.out.println("The specialty was deleted successful!");
        } catch (NoSuchElementException e) {
            System.out.println("There is no any specialty with such id!");
        }

    }

    public void runCommand(String command) {
        switch (command) {
            case "get_all" -> printAllSpecialtys();
            case "get_one" -> printOneSpecialty();
            case "save" -> saveAndPrintSpecialty();
            case "update" -> updateAndPrintSpecialty();
            case "delete" -> deleteSpecialtyAndPrintWasOperationSuccessful();
            default -> System.out.println("Invalid command, try again!");
        }
    }
}
