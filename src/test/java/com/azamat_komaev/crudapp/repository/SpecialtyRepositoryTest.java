package com.azamat_komaev.crudapp.repository;

import com.azamat_komaev.crudapp.model.Specialty;
import com.azamat_komaev.crudapp.model.Status;
import com.azamat_komaev.crudapp.repository.gson.GsonSpecialtyRepositoryImpl;
import com.azamat_komaev.crudapp.util.RepositoryTestUtil;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

import static org.junit.Assert.*;

public class SpecialtyRepositoryTest {
    private final SpecialtyRepository repository = new GsonSpecialtyRepositoryImpl();
    private final String FILE_PATH = "src/main/resources/specialties.json";
    
    @Before
    @After
    public void clearFileContent() {
        RepositoryTestUtil.clearFileContent(this.FILE_PATH);
    }
    
    private void saveThreeSpecialties() {
        Specialty specialty1 = new Specialty();
        specialty1.setName("Programmer");
        Specialty specialty2 = new Specialty();
        specialty2.setName("Manager");
        Specialty specialty3 = new Specialty();
        specialty3.setName("HR");

        this.repository.save(specialty1);
        this.repository.save(specialty2);
        this.repository.save(specialty3);
    }

    @Test
    public void testAddSpecialties() {
        saveThreeSpecialties();

        List<Specialty> specialtyListFromRepository = this.repository.getAll();
        Specialty[] specialtyArrayFromGetById = {
            this.repository.getById(1),
            this.repository.getById(2),
            this.repository.getById(3)
        };

        assertEquals(3, specialtyListFromRepository.size());
        assertArrayEquals(specialtyArrayFromGetById,
                          specialtyListFromRepository.toArray());
    }

    @Test
    public void testGetSpecialties() {
        saveThreeSpecialties();

        assertNull(this.repository.getById(-1));
        assertNull(this.repository.getById(0));
        assertNull(this.repository.getById(4));

        assertNotNull(this.repository.getById(1));
        assertNotNull(this.repository.getById(2));
        assertNotNull(this.repository.getById(3));
    }

    @Test
    public void testCheckSpecialtyModelFields() {
        saveThreeSpecialties();

        Specialty specialty = this.repository.getById(2);

        assertNotNull(specialty);
        assertEquals(Integer.valueOf(2), specialty.getId());
        assertEquals("Manager", specialty.getName());
        assertEquals(Status.ACTIVE, specialty.getStatus());
    }

    @Test
    public void testUpdateSpecialty() {
        saveThreeSpecialties();

        Specialty specialtyBeforeUpdate = this.repository.getById(2);
        Specialty specialtyAfterUpdate;
        Specialty specialtyToUpdate = new Specialty();

        specialtyToUpdate.setId(2);
        specialtyToUpdate.setName("Driver");
        this.repository.update(specialtyToUpdate);
        specialtyAfterUpdate = this.repository.getById(2);

        assertNotEquals(specialtyBeforeUpdate, specialtyAfterUpdate);
        assertEquals(Integer.valueOf(2), specialtyAfterUpdate.getId());
        assertEquals("Driver", specialtyAfterUpdate.getName());
        assertEquals(Status.ACTIVE, specialtyAfterUpdate.getStatus());
    }

    @Test
    public void testDeleteSpecialty() {
        saveThreeSpecialties();

        int specialtyListCount = this.repository.getAll().size();

        Specialty specialtyBeforeDelete = this.repository.getById(3);
        this.repository.deleteById(3);
        Specialty specialtyAfterDelete = this.repository.getById(3);

        assertEquals(3, specialtyListCount);
        assertNotEquals(specialtyBeforeDelete, specialtyAfterDelete);
        assertEquals(Integer.valueOf(3), specialtyAfterDelete.getId());
        assertEquals(specialtyBeforeDelete.getName(), specialtyAfterDelete.getName());
        assertEquals(Status.DELETED, specialtyAfterDelete.getStatus());
    }
}
