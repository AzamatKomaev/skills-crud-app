package com.azamat_komaev.crudapp.repository.gson;

import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.service.RepositoryService;

import java.util.*;

public class GsonSkillRepositoryImpl implements SkillRepository {
    private final RepositoryService<Skill> service;

    public GsonSkillRepositoryImpl() {
        this.service = new RepositoryService<>("src/main/resources/skills.json");
    }

    private Integer generateNewId(List<Skill> skills) {
        Skill maxIdSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;
    }

    @Override
    public Skill getById(Integer id) {
        return this.service.getItemsFromFile(Skill.class).stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return this.service.getItemsFromFile(Skill.class);
    }

    @Override
    public Skill save(Skill skillToSave) {
        List<Skill> currentSkills = this.service.getItemsFromFile(Skill.class);

        Integer id = generateNewId(currentSkills);
        skillToSave.setId(id);
        currentSkills.add(skillToSave);
        this.service.addItemToFile(currentSkills);
        return skillToSave;
    }

    @Override
    public Skill update(Skill skill) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {}
}

