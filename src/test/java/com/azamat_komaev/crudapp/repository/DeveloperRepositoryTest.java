package com.azamat_komaev.crudapp.repository;

import com.azamat_komaev.crudapp.repository.TestService;
import com.azamat_komaev.crudapp.repository.gson.GsonDeveloperRepositoryImpl;
import org.junit.*;


public class DeveloperRepositoryTest {
    private final DeveloperRepository repository = new GsonDeveloperRepositoryImpl();
    private final String FILE_PATH = "src/main/resources/developers.json";

    @Before
    @After
    public void clearFileContent() {
        TestService.clearFileContent(this.FILE_PATH);
    }
}
