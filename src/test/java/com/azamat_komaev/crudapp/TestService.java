package com.azamat_komaev.crudapp.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestService {
    /**
     * Clear file content by path writing empty byte array.
     * @param filePath path to file should be cleared
     */
    public static void clearFileContent(String filePath) {
        try {
            Files.write(Paths.get(filePath), new byte[]{});
        } catch (IOException e) {
            System.out.println("Cannot clear " + filePath);
        }
    }
}
