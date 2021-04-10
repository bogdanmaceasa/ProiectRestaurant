package com.siit.finalproject.details.detailsTextProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class InputTextToFile {

    static String separator = File.separator;
    static String fileLocation = "src" + separator + "main" + separator + "resources" + separator;

    public static String createOrUpdateFile (String fileName, String content){

        Path filePath = Paths.get(fileLocation + fileName + ".txt");

        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
            Arrays.stream(content
                    .split("%n"))
                    .forEach(b -> {
                        try {
                            Files.write(filePath,(b+System.lineSeparator()).getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

    } catch (IOException e) {
        e.printStackTrace();
    }
        return filePath.toString();

    }
}
