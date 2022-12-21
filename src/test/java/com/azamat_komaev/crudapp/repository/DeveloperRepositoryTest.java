package com.azamat_komaev.crudapp.repository;

import com.azamat_komaev.crudapp.model.Developer;
import com.azamat_komaev.crudapp.model.Skill;
import com.azamat_komaev.crudapp.model.Specialty;
import com.azamat_komaev.crudapp.model.Status;
import com.azamat_komaev.crudapp.repository.gson.GsonDeveloperRepositoryImpl;
import com.azamat_komaev.crudapp.util.RepositoryTestUtil;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeveloperRepositoryTest {
    private final DeveloperRepository repository = new GsonDeveloperRepositoryImpl();
    private final String FILE_PATH = "src/main/resources/developers.json";
    private final List<Skill> skillList = new ArrayList<Skill>();
    private final List<Specialty> specialtyList = new ArrayList<Specialty>();

    @Before
    @After
    public void clearFileContent() {
        RepositoryTestUtil.clearFileContent(this.FILE_PATH);
    }

    private void saveTwoDevelopers() {
        this.specialtyList.add(new Specialty(1, "Programmer"));
        this.specialtyList.add(new Specialty(2, "Manager"));

        this.skillList.add(new Skill(1, "Soft-Skills"));
        this.skillList.add(new Skill(2, "Hard-Skills"));
        this.skillList.add(new Skill(3, "No-Code"));

        Developer developer1 = new Developer(1, "Azamat", "Komaev",
                                             this.skillList, this.specialtyList.get(0));
        Developer developer2 = new Developer(2, "Sasha", "Mironov",
                                             this.skillList, this.specialtyList.get(1));

        this.repository.save(developer1);
        this.repository.save(developer2);
    }

    @Test
    public void testAddDevelopers() {
        saveTwoDevelopers();

        List<Developer> developerListFromRepository = this.repository.getAll();
        Developer[] developerArrayFromGetById = {
            this.repository.getById(1),
            this.repository.getById(2)
        };

        assertEquals(2, developerListFromRepository.size());
        assertArrayEquals(developerArrayFromGetById, developerListFromRepository.toArray());
    }

    @Test
    public void testGetDevelopers() {
        saveTwoDevelopers();

        assertNull(this.repository.getById(-1));
        assertNull(this.repository.getById(0));
        assertNull(this.repository.getById(3));

        assertNotNull(this.repository.getById(1));
        assertNotNull(this.repository.getById(2));
    }

    @Test
    public void testCheckDeveloperModelField() {
        saveTwoDevelopers();

        Developer developer = this.repository.getById(1);

        assertNotNull(developer);
        assertEquals(Integer.valueOf(1), developer.getId());
        assertEquals("Azamat", developer.getFirstName());
        assertEquals("Komaev", developer.getLastName());
        assertEquals(this.skillList, developer.getSkills());
        assertEquals(this.specialtyList.get(0), developer.getSpecialty());
    }

    @Test
    public void testUpdateDeveloper() {
        saveTwoDevelopers();

        Developer developerBeforeUpdate = this.repository.getById(1);
        Developer developerAfterUpdate;
        Developer developerToUpdate = new Developer();

        List<Skill> developerSkillListToUpdate = new ArrayList<Skill>();
        developerSkillListToUpdate.add(new Skill(1, "Coding"));

        Specialty developerSpecialtyToUpdate = new Specialty(1, "HR");

        developerToUpdate.setId(1);
        developerToUpdate.setFirstName("David");
        developerToUpdate.setLastName("Guetta");
        developerToUpdate.setSkills(developerSkillListToUpdate);
        developerToUpdate.setSpecialty(developerSpecialtyToUpdate);

        this.repository.update(developerToUpdate);
        developerAfterUpdate = this.repository.getById(1);

        assertNotEquals(developerBeforeUpdate, developerAfterUpdate);
        assertEquals(Integer.valueOf(1), developerAfterUpdate.getId());
        assertEquals("David", developerAfterUpdate.getFirstName());
        assertEquals("Guetta", developerAfterUpdate.getLastName());
        assertEquals(developerSkillListToUpdate, developerAfterUpdate.getSkills());
        assertEquals(developerSpecialtyToUpdate, developerAfterUpdate.getSpecialty());
        assertEquals(Status.ACTIVE, developerAfterUpdate.getStatus());
    }

    @Test
    public void testDeleteDeveloper() {
        saveTwoDevelopers();

        int developerListCount = this.repository.getAll().size();
        Developer developerBeforeDelete = this.repository.getById(1);
        this.repository.deleteById(1);
        Developer developerAfterDelete = this.repository.getById(1);

        assertEquals(2, developerListCount);
        assertNotEquals(developerBeforeDelete, developerAfterDelete);
        assertEquals(Integer.valueOf(1), developerAfterDelete.getId());
        assertEquals(Status.DELETED, developerAfterDelete.getStatus());
    }
}
