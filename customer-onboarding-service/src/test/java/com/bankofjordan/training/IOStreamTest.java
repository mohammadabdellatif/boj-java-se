package com.bankofjordan.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.stream.Stream;

public class IOStreamTest {

    public static void main(String[] args) throws IOException {
        //InputStream is; // to read data from a file or network resource.
        //OutputStream os;// to write data to a file or network resource.
        Path path = Paths.get(".", "temp", "text.txt");
        // try with resource
        Files.write(path, "Hello World! How are you".getBytes());
        Files.write(path, Arrays.asList(
                "Hello World! How are you",
                "I am fine thank you"), StandardOpenOption.TRUNCATE_EXISTING);
        String read = Files.readString(path);
        System.out.println(read);
        System.out.println("=====using buffered readers=====");
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        }
//        ioAsBytes(path);
//        System.out.println("done, walk through current folder");
//        Stream<Path> walk = Files.walk(Paths.get("."));
//        walk.forEach(System.out::println);

        // Visitor design pattern
        Path tempDir = Paths.get(".", "temp");
        System.out.println("=====start walking into" + tempDir + "=====");
        Files.walkFileTree(tempDir, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("pre-dir: " + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("file: " + file);
                if(file.getFileName().toString().equals("a.txt")) {
                    System.out.println("found a.txt, terminating walk");
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.TERMINATE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("post-dir: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void ioAsBytes(Path path) throws IOException {
        try (OutputStream os = Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            os.write(new byte[]{'S', 'a', 'l', 'e', 'm'});
            byte[] asBytes = ": this is the test message".getBytes();
            os.write(asBytes);
            os.flush();
        }

        try (InputStream is = Files.newInputStream(path)) {
            int data = is.read();
            StringBuilder sb = new StringBuilder();
            while (data != -1) {
                byte b = (byte) data;
                sb.append((char) b);
                data = is.read();
            }
            System.out.println(sb.toString());
        }
    }
}
