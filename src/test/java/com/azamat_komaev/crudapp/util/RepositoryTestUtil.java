package com.azamat_komaev.crudapp.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RepositoryTestUtil {
    public static void clearFileContent(String filePath) {
        try {
            Files.write(Paths.get(filePath), new byte[]{});
        } catch (IOException e) {
            System.out.println("Cannot clear " + filePath);
        }
    }
}
