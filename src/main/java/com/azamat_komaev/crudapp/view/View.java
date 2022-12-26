package com.azamat_komaev.crudapp.view;

import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class View<T> {
    protected final Object controller;
    protected final Scanner scanner;

    protected View() {
        this.controller = null;
        this.scanner = new Scanner(System.in);
    }

    protected void printAll() {
        System.out.println(this.controller.getAll());
    }

    protected void printOne() {
        System.out.print("Enter model id should be gotten: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());
        T modelToPrint;

        try {
            modelToPrint = this.controller.getOne(id);
            System.out.println(modelToPrint);
        } catch (NoSuchElementException e) {
            System.out.println("There is no any model with such id!");
        }
    }

    protected void deleteAndPrintWasOperationSuccessful() {
        System.out.print("Enter model id should be deleted: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());

        try {
            this.controller.delete(id);
            System.out.println("The developer was deleted successful!");
        } catch (NoSuchElementException e) {
            System.out.println("There is no any developer with such id!");
        }
    }

    abstract void saveAndPrint();

    abstract void updateAndPrint();

    void runCommand(String command) {
        switch (command) {
            case "get_all" -> printAll();
            case "get_one" -> printOne();
            case "create" -> saveAndPrint();
            case "update" -> updateAndPrint();
            case "delete" -> deleteAndPrintWasOperationSuccessful();
            default -> System.out.println("Invalid command, try again!");
        }
    }
}
