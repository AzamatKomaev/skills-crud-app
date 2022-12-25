package com.azamat_komaev.crudapp.util;

import com.azamat_komaev.crudapp.model.Developer;
import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.model.Specialty;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.repository.SpecialtyRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonSkillRepositoryImpl;
import com.azamat_komaev.crudapp.repository.gson.GsonSpecialtyRepositoryImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeveloperControllerUtil {
    public static List<Skill> getSKillListByIds(List<Integer> ids) {
        SkillRepository skillRepository = new GsonSkillRepositoryImpl();
        List<Skill> skillList = new ArrayList<>();

        ids.forEach(id -> skillList.add(skillRepository.getById(id)));
        return skillList;
    }

    public static Specialty getSpecialtyById(Integer id) {
        SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImpl();
        return specialtyRepository.getById(id);
    }

    public static void setDeveloperModelFields(Developer developer, String firstName, String lastName,
                                                    List<Skill> skillList, Specialty specialty) {
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSkills(skillList);
        developer.setSpecialty(specialty);
    }
}
