package com.azamat_komaev.crudapp.service;

import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.model.Specialty;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.repository.SpecialtyRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonSkillRepositoryImpl;
import com.azamat_komaev.crudapp.repository.gson.GsonSpecialtyRepositoryImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DeveloperViewService {
    private final Scanner scanner;

    public DeveloperViewService() {
        this.scanner = new Scanner(System.in);
    }

    public Integer readAndParseId() {
        System.out.print("Enter developer id should be updated: ");
        return Integer.parseInt(this.scanner.nextLine());
    }

    public String readAndParseFirstName() {
        System.out.print("Enter firstName: ");
        return scanner.nextLine();
    }

    public String readAndParseLastName() {
        System.out.print("Enter lastName: ");
        return scanner.nextLine();
    }

    public List<Skill> readAndParseSkillList() {
        System.out.print("Enter list of skill ids seperated with spaces: ");
        String[] skillsIdsString = this.scanner.nextLine().split(" ");

        List<Integer> skillsIds = Arrays.stream(skillsIdsString)
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();

        SkillRepository repository = new GsonSkillRepositoryImpl();
        List<Skill> skillList = new ArrayList<>();
        skillsIds.forEach(id -> skillList.add(repository.getById(id)));
        return skillList;
    }

    public Specialty readAndParseSpecialty() {
        System.out.print("Enter specialty id: ");
        Integer specialtyId = Integer.parseInt(this.scanner.nextLine());

        SpecialtyRepository repository = new GsonSpecialtyRepositoryImpl();
        return repository.getById(specialtyId);
    }
}
