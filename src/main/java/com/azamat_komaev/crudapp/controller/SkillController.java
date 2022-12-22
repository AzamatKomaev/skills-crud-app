package com.azamat_komaev.crudapp.controller;

import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private final SkillRepository repository;

    public SkillController() {
        this.repository = new GsonSkillRepositoryImpl();
    }

    public List<Skill> getAll() {
        return this.repository.getAll();
    }

    public Skill getOne(Integer id) {
        return this.repository.getById(id);
    }

    public Skill save(String name) {
        Skill skillToSave = new Skill();
        skillToSave.setName(name);
        return this.repository.save(skillToSave);
    }

    public Skill update(Integer id, String name) {
        Skill skillToUpdate = new Skill(id, name);
        return this.repository.update(skillToUpdate);
    }

    public void destroy(Integer id) {
        this.repository.deleteById(id);
    }
}

