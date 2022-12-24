package com.azamat_komaev.crudapp.view;

import com.azamat_komaev.crudapp.controller.SkillController;
import com.azamat_komaev.crudapp.model.Skill;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SkillView {
    private final SkillController controller;
    private final Scanner scanner;

    public SkillView() {
        this.controller = new SkillController();
        this.scanner = new Scanner(System.in);
    }

    private void printAllSkills() {
        System.out.println(this.controller.getAll());
    }

    private void printOneSkill() {
        System.out.print("Enter skill id should be gotten: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());
        Skill skillToPrint;

        try {
            skillToPrint = this.controller.getOne(id);
            System.out.println(skillToPrint);
        } catch (NoSuchElementException e) {
            System.out.println("There is no any skill with such id!");
        }

    }

    private void saveAndPrintSkill() {
        System.out.print("Enter name for new skill: ");
        String name = this.scanner.nextLine();

        Skill newSkill = this.controller.save(name);

        System.out.println(newSkill);
    }

    private void updateAndPrintSkill() {
        System.out.print("Enter skill id should be updated: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());
        System.out.print("Enter new name for skill you want to update: ");
        String name = this.scanner.nextLine();

        try {
            Skill skillToPrint = this.controller.update(id, name);
            System.out.println(skillToPrint);
        } catch (NoSuchElementException e) {
            System.out.println("There is no any skill with such id!");
        }

    }

    private void deleteSkillAndPrintWasOperationSuccessful() {
        System.out.print("Enter skill id should be deleted: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());

        try {
            this.controller.destroy(id);
            System.out.println("The skill was deleted successful!");
        } catch (NoSuchElementException e) {
            System.out.println("There is no any skill with such id!");
        }

    }

    public void runCommand(String command) {
        switch (command) {
            case "get_all" -> printAllSkills();
            case "get_one" -> printOneSkill();
            case "save" -> saveAndPrintSkill();
            case "update" -> updateAndPrintSkill();
            case "delete" -> deleteSkillAndPrintWasOperationSuccessful();
            default -> System.out.println("Invalid command, try again!");
        }
    }
}
