package com.azamat_komaev.crudapp.repository;

import com.azamat_komaev.crudapp.model.Developer;
import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.model.Specialty;
import com.azamat_komaev.crudapp.repository.gson.GsonDeveloperRepositoryImpl;
import com.azamat_komaev.crudapp.util.RepositoryTestUtil;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeveloperRepositoryTest {
    private final DeveloperRepository repository = new GsonDeveloperRepositoryImpl();
    private final String FILE_PATH = "src/main/resources/developers.json";

    @Before
    @After
    public void clearFileContent() {
        RepositoryTestUtil.clearFileContent(this.FILE_PATH);
    }

    private void saveThreeDevelopers() {
        List<Skill> skillList = new ArrayList<Skill>();
        Specialty specialty1 = new Specialty(1, "Programmer");
        Specialty specialty2 = new Specialty(2, "Manager");

        skillList.add(new Skill(1, "Soft-Skills"));
        skillList.add(new Skill(2, "Hard-Skills"));
        skillList.add(new Skill(3, "No-Code"));

        Developer developer1 = new Developer();
        developer1.setFirstName("Azamat");
        developer1.setLastName("Komaev");
        developer1.setSkills(skillList);
        developer1.setSpecialty(specialty1);

    }
}
