package com.azamat_komaev.crudapp.repository.gson;

import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.model.Status;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.service.RepositoryService;

import java.util.*;
import java.util.stream.Collectors;

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
        if (skillToSave.getName() == null) {
            throw new NullPointerException();
        }

        List<Skill> currentSkills = this.service.getItemsFromFile(Skill.class);
        Integer id = generateNewId(currentSkills);
        skillToSave.setId(id);
        currentSkills.add(skillToSave);
        this.service.addItemsToFile(currentSkills);
        return skillToSave;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> currentSkills = this.service.getItemsFromFile(Skill.class);

        currentSkills = currentSkills.stream()
            .map(s -> Objects.equals(s.getId(), skill.getId()) ? skill : s)
            .collect(Collectors.toList());

        this.service.addItemsToFile(currentSkills);
        return skill;

    }

    @Override
    public void deleteById(Integer id) {
        List<Skill> currentSkills = this.service.getItemsFromFile(Skill.class);

        currentSkills = currentSkills.stream()
            .peek(s -> {
                if (!Objects.equals(s.getId(), id)) {
                    s.setStatus(Status.DELETED);
                }
            })
            .collect(Collectors.toList());

        this.service.addItemsToFile(currentSkills);
    }
}

