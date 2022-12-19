package com.azamat_komaev.crudapp;

import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class AppRunner {

    public static void main(String[] args) {
        Skill skill = new Skill();
        SkillRepository repository = new GsonSkillRepositoryImpl();
        repository.save(skill);
    }
}
