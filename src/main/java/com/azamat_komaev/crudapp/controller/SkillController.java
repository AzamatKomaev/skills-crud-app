package com.azamat_komaev.crudapp.controller;

import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;

public class SkillController {
    private final SkillRepository repository;

    public SkillController() {
        this.repository = new GsonSkillRepositoryImpl();
    }

    public List<Skill> getAll() {
        return this.repository.getAll();
    }

    public Skill getOne(Integer id) throws NoSuchElementException {
        Skill skill = this.repository.getById(id);

        if (skill == null) {
            throw new NoSuchElementException("There is no such element in database!");
        }

        return skill;
    }

    public Skill save(String name) throws NoSuchElementException {
        Skill skillToSave = new Skill();
        skillToSave.setName(name);
        return this.repository.save(skillToSave);
    }

    public Skill update(Integer id, String name) throws NoSuchElementException {
        Skill skillToUpdate = getOne(id);
        skillToUpdate.setName(name);
        return this.repository.update(skillToUpdate);
    }

    public void destroy(Integer id) throws NoSuchElementException {
        getOne(id);
        this.repository.deleteById(id);
    }
}

