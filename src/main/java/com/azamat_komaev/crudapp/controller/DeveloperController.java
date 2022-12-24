package com.azamat_komaev.crudapp.controller;

import com.azamat_komaev.crudapp.model.Developer;
import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.model.Specialty;
import com.azamat_komaev.crudapp.repository.DeveloperRepository;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonDeveloperRepositoryImpl;
import com.azamat_komaev.crudapp.repository.gson.GsonSkillRepositoryImpl;
import com.azamat_komaev.crudapp.util.DeveloperControllerUtil;

import java.util.ArrayList;
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
                          List<Integer> skillIds, Integer specialtyId) throws NoSuchElementException {
        Developer developerToSave = new Developer();

        List<Skill> skillsListToSave = DeveloperControllerUtil.getSKillListByIds(skillIds);
        Specialty specialtyToSave = DeveloperControllerUtil.getSpecialtyById(specialtyId);

        DeveloperControllerUtil.setDeveloperModelFields(developerToSave, firstName, lastName,
                                                        skillsListToSave, specialtyToSave);

        return this.repository.save(developerToSave);
    }

    public Developer update(Integer id, String firstName, String lastName,
                            List<Integer> skillIds, Integer specialtyId) throws NoSuchElementException {
        Developer developerToUpdate = getOne(id);

        List<Skill> skillsListToUpdate = DeveloperControllerUtil.getSKillListByIds(skillIds);
        Specialty specialtyToUpdate = DeveloperControllerUtil.getSpecialtyById(specialtyId);

        DeveloperControllerUtil.setDeveloperModelFields(developerToUpdate, firstName, lastName,
                                                        skillsListToUpdate, specialtyToUpdate);

        return this.repository.update(developerToUpdate);
    }

    public void destroy(Integer id) throws NoSuchElementException {
        getOne(id);
        this.repository.deleteById(id);
    }
}

