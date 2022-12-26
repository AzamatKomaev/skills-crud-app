package com.azamat_komaev.crudapp.view;

import com.azamat_komaev.crudapp.controller.DeveloperController;
import com.azamat_komaev.crudapp.model.Developer;
import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.model.Specialty;
import com.azamat_komaev.crudapp.service.DeveloperViewService;
import com.azamat_komaev.crudapp.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DeveloperView implements GenericView {
    public final DeveloperController controller;
    private final DeveloperViewService service;
    private final Scanner scanner;

    public DeveloperView() {
        this.controller = new DeveloperController();
        this.service = new DeveloperViewService();
        this.scanner = new Scanner(System.in);
    }

    public void printAll() {
        System.out.println(this.controller.getAll());
    }

    public void printOne() {
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

    public void saveAndPrint() {
        String firstName = this.service.readAndParseFirstName();
        String lastName = this.service.readAndParseLastName();
        List<Skill> skillList = this.service.readAndParseSkillList();
        Specialty specialty = this.service.readAndParseSpecialty();

        Developer newDeveloper = this.controller.save(firstName, lastName, skillList, specialty);
        System.out.println(newDeveloper);
    }

    public void updateAndPrint() {
        Integer id = this.service.readAndParseId();
        String firstName = this.service.readAndParseFirstName();
        String lastName = this.service.readAndParseLastName();
        List<Skill> skillList = this.service.readAndParseSkillList();
        Specialty specialty = this.service.readAndParseSpecialty();

        try {
            Developer developerToPrint = this.controller.update(id, firstName, lastName, skillList, specialty);
            System.out.println(developerToPrint);
        } catch (NoSuchElementException e) {
            System.out.println("There is no any developer with such id!");
        }
    }

    public void deleteAndPrintWasOperationSuccessful() {
        System.out.print("Enter developer id should be deleted: ");
        Integer id = Integer.parseInt(this.scanner.nextLine());

        try {
            this.controller.destroy(id);
            System.out.println("The developer was deleted successful!");
        } catch (NoSuchElementException e) {
            System.out.println("There is no any developer with such id!");
        }
    }
}
