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

    public void printAll() {
        System.out.println(this.controller.getAll());
    }

    public void printOne() {
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

    public void saveAndPrint() {
        System.out.print("Enter name for new specialty: ");
        String name = this.scanner.nextLine();

        Specialty newSpecialty = this.controller.save(name);

        System.out.println(newSpecialty);
    }

    public void updateAndPrint() {
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

    public void deleteAndPrintWasOperationSuccessful() {
        System.out.print("Enter specialty id should be deleted: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());

        try {
            this.controller.destroy(id);
            System.out.println("The specialty was deleted successful!");
        } catch (NoSuchElementException e) {
            System.out.println("There is no any specialty with such id!");
        }
    }
}
