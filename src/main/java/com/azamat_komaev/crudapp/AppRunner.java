package com.azamat_komaev.crudapp;

import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.repository.SkillRepository;
import com.azamat_komaev.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class AppRunner {
    static void testSkills() {
        SkillRepository repository = new GsonSkillRepositoryImpl();

        // Add skills to file
        Skill skill1 = new Skill();
        skill1.setName("Soft-Skills");
        Skill skill2 = new Skill();
        skill2.setName("Hard-Skills");
        Skill skill3 = new Skill();
        skill3.setName("Programming");
        repository.save(skill1);
        repository.save(skill2);
        repository.save(skill3);

        // Get all skills from file
        List<Skill> skills = repository.getAll();
        System.out.println(skills);

        // Get each skill from file by id
        System.out.println(repository.getById(1));
        System.out.println(repository.getById(2));
        System.out.println(repository.getById(3));


    }

    public static void main(String[] args) {
        testSkills();
    }
}
