package com.azamat_komaev.crudapp.repository;

import com.azamat_komaev.crudapp.repository.gson.GsonDeveloperRepositoryImpl;
import com.azamat_komaev.crudapp.util.RepositoryTestUtil;
import org.junit.*;


public class DeveloperRepositoryTest {
    private final DeveloperRepository repository = new GsonDeveloperRepositoryImpl();
    private final String FILE_PATH = "src/main/resources/developers.json";

    @Before
    @After
    public void clearFileContent() {
        RepositoryTestUtil.clearFileContent(this.FILE_PATH);
    }
}
