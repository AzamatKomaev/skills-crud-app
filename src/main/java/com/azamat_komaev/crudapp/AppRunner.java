package com.azamat_komaev.crudapp;

import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonSkillRepositoryImpl;

public class AppRunner {
    public static void main(String[] args) {
        SkillRepository repository = new GsonSkillRepositoryImpl();
        Skill skill = new Skill(1, "Google!");
        repository.save(skill);

    }
}
