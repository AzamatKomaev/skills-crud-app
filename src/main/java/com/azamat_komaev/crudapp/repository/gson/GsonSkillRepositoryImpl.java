package com.azamat_komaev.crudapp.repository.gson;

import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GsonSkillRepositoryImpl implements SkillRepository {
    private final String SKILL_FILE_PATH = "src/main/resources/skills.json";
    private final Gson GSON = new Gson();

    private List<Skill> readSkillsFromFile() {
        String fileContent;
        List<Skill> skillList;
        List<Skill> defaultList = new ArrayList<>(Collections.emptyList());

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(SKILL_FILE_PATH)));
            Type type = new TypeToken<ArrayList<Skill>>(){}.getType();
            skillList = GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        System.out.println(skillList == null ? defaultList : skillList);
        return skillList == null ? defaultList : skillList;
    }

    private void writeSkillsToFile(List<Skill> skills) {
        String json = GSON.toJson(skills);
        try {
            Files.write(Paths.get(SKILL_FILE_PATH), json.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateNewId(List<Skill> skills) {
        if (skills == null) {
            return 1;
        }

        Skill maxIdSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;

    }

    @Override
    public Skill getById(Integer id) {
        return readSkillsFromFile().stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return readSkillsFromFile();
    }

    @Override
    public Skill save(Skill skillToSave) {
        List<Skill> currentSkills = readSkillsFromFile();

        Integer id = generateNewId(currentSkills);
        skillToSave.setId(id);
        currentSkills.add(skillToSave);
        writeSkillsToFile(currentSkills);
        return skillToSave;
    }

    @Override
    public Skill update(Skill skill) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {}
}

