package com.bankofjordan.training;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOPathTest {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".", "temp", "text.txt");
        System.out.println(path);
        System.out.println(path.getParent());
        System.out.println(path.getName(1));
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        System.out.println(path.getNameCount());
        System.out.println(path.toAbsolutePath().normalize());
        if(Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        if(Files.notExists(path)) {
            Files.createFile(path);
        }
        //        usingOldJavaIO();
        System.out.println("done");
    }

    private static void usingOldJavaIO() throws IOException {
        // you are defining a pointer to a file
        // for a directory or a file
        File dir = new File("./temp");
        File file = new File(dir, "text.txt");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println("done");
        System.out.println(file);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
    }
}
