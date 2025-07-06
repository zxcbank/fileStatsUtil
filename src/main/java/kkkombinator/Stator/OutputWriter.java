package kkkombinator.Stator;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class OutputWriter {
    public static void clearFile(String filePath) {
        try (FileWriter fw = new FileWriter(filePath, false)) {
        } catch (IOException e) {
            System.err.println("не удалось очистить файл" + filePath);
        }
    }

    public static <T> void fileWriteArg(String filePath, T arg, boolean flush) {
        var path = Paths.get(filePath);

        if (!Files.exists(path)) {
            try {
                Path createdFile = Files.createFile(path);
                Files.write(createdFile, arg.toString().getBytes(), StandardOpenOption.APPEND);
                Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println("не удалось создать файл или записать в файл по пути: " + filePath);
            }
        } else {
            try {
                if (flush) {
                    clearFile(filePath);
                }
                Files.write(path, arg.toString().getBytes(), StandardOpenOption.APPEND);
                Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
            } catch( IOException e) {
                System.err.println("не удалось записать информацию в файл по пути: " + filePath);
            }
        }
    }
}
