package com.azamat_komaev.crudapp.controller;

import com.azamat_komaev.crudapp.model.Developer;
import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.model.Specialty;
import com.azamat_komaev.crudapp.repository.DeveloperRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonDeveloperRepositoryImpl;
import com.azamat_komaev.crudapp.util.DeveloperControllerUtil;

import java.util.List;
import java.util.NoSuchElementException;

public class DeveloperController {
    private final DeveloperRepository repository;

    public DeveloperController() {
        this.repository = new GsonDeveloperRepositoryImpl();
    }

    public List<Developer> getAll() {
        return this.repository.getAll();
    }

    public Developer getOne(Integer id) throws NoSuchElementException {
        Developer developer = this.repository.getById(id);

        if (developer == null) {
            throw new NoSuchElementException("There is no such element in database!");
        }

        return developer;
    }

    public Developer save(String firstName, String lastName,
                          List<Skill> skillList, Specialty specialty) {
        Developer developerToSave = new Developer();

        DeveloperControllerUtil.setDeveloperModelFields(developerToSave, firstName,
                                                        lastName, skillList, specialty);

        return this.repository.save(developerToSave);
    }

    public Developer update(Integer id, String firstName, String lastName,
                            List<Skill> skillList, Specialty specialty) throws NoSuchElementException {
        Developer developerToUpdate = getOne(id);

        DeveloperControllerUtil.setDeveloperModelFields(developerToUpdate, firstName,
                                                        lastName, skillList, specialty);

        return this.repository.update(developerToUpdate);
    }

    public void destroy(Integer id) throws NoSuchElementException {
        getOne(id);
        this.repository.deleteById(id);
    }
}

