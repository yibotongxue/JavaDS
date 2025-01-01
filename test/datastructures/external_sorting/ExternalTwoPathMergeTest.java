package datastructures.external_sorting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;

class ExternalTwoPathMergeTest {

    @AfterEach
    void tearDown() {
        // 删除所有txt和txt1文件
        File[] files = new File(".").listFiles((_, name) -> name.endsWith(".txt") || name.endsWith(".txt1"));
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    @Test
    void mergeFiles_withValidFiles_shouldMergeCorrectly() throws IOException {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File outputFile = new File("output.txt");

        Files.write(file1.toPath(), "1\n3\n5\n".getBytes());
        Files.write(file2.toPath(), "2\n4\n6\n".getBytes());

        ExternalTwoPathMerge.mergeFiles(file1, file2, outputFile);

        String expected = "1\n2\n3\n4\n5\n6\n";
        String actual = Files.readString(outputFile.toPath());
        assertEquals(expected, actual);
    }

    @Test
    void mergeFiles_withEmptyFile1_shouldMergeCorrectly() throws IOException {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File outputFile = new File("output.txt");

        Files.write(file1.toPath(), "".getBytes());
        Files.write(file2.toPath(), "2\n4\n6\n".getBytes());

        ExternalTwoPathMerge.mergeFiles(file1, file2, outputFile);

        String expected = "2\n4\n6\n";
        String actual = Files.readString(outputFile.toPath());
        assertEquals(expected, actual);
    }

    @Test
    void mergeFiles_withEmptyFile2_shouldMergeCorrectly() throws IOException {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File outputFile = new File("output.txt");

        Files.write(file1.toPath(), "1\n3\n5\n".getBytes());
        Files.write(file2.toPath(), "".getBytes());

        ExternalTwoPathMerge.mergeFiles(file1, file2, outputFile);

        String expected = "1\n3\n5\n";
        String actual = Files.readString(outputFile.toPath());
        assertEquals(expected, actual);
    }

    @Test
    void mergeFiles_withBothFilesEmpty_shouldCreateEmptyOutput() throws IOException {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File outputFile = new File("output.txt");

        Files.write(file1.toPath(), "".getBytes());
        Files.write(file2.toPath(), "".getBytes());

        ExternalTwoPathMerge.mergeFiles(file1, file2, outputFile);

        String expected = "";
        String actual = Files.readString(outputFile.toPath());
        assertEquals(expected, actual);
    }

    @Test
    void mergeFiles_withNonNumericContent_shouldThrowNumberFormatException() throws IOException {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File outputFile = new File("output.txt");

        Files.write(file1.toPath(), "a\nb\nc\n".getBytes());
        Files.write(file2.toPath(), "d\ne\nf\n".getBytes());

        assertThrows(NumberFormatException.class, () -> ExternalTwoPathMerge.mergeFiles(file1, file2, outputFile));
    }

    @Test
    void merge_withSingleInputFile_shouldCopyFile() throws IOException {
        String[] inputFiles = {"input1.txt"};
        String outputFile = "output.txt";

        Files.write(new File(inputFiles[0]).toPath(), "1\n2\n3\n".getBytes());

        ExternalTwoPathMerge.merge(inputFiles, outputFile);

        String expected = "1\n2\n3\n";
        String actual = Files.readString(new File(outputFile).toPath());
        assertEquals(expected, actual);
    }

    @Test
    void merge_withMultipleInputFiles_shouldMergeCorrectly() throws IOException {
        String[] inputFiles = {"input1.txt", "input2.txt"};
        String outputFile = "output.txt";

        Files.write(new File(inputFiles[0]).toPath(), "1\n3\n5\n".getBytes());
        Files.write(new File(inputFiles[1]).toPath(), "2\n4\n6\n".getBytes());

        ExternalTwoPathMerge.merge(inputFiles, outputFile);

        String expected = "1\n2\n3\n4\n5\n6\n";
        String actual = Files.readString(new File(outputFile).toPath());
        assertEquals(expected, actual);
    }

    @Test
    void merge_withEmptyInputFiles_shouldCreateEmptyOutput() throws IOException {
        String[] inputFiles = {"input1.txt", "input2.txt"};
        String outputFile = "output.txt";

        Files.write(new File(inputFiles[0]).toPath(), "".getBytes());
        Files.write(new File(inputFiles[1]).toPath(), "".getBytes());

        ExternalTwoPathMerge.merge(inputFiles, outputFile);

        String expected = "";
        String actual = Files.readString(new File(outputFile).toPath());
        assertEquals(expected, actual);
    }

    @Test
    void merge_withNonNumericContent_shouldThrowNumberFormatException() throws IOException {
        String[] inputFiles = {"input1.txt", "input2.txt"};
        String outputFile = "output.txt";

        Files.write(new File(inputFiles[0]).toPath(), "a\nb\nc\n".getBytes());
        Files.write(new File(inputFiles[1]).toPath(), "d\ne\nf\n".getBytes());

        assertThrows(NumberFormatException.class, () -> ExternalTwoPathMerge.merge(inputFiles, outputFile));
    }
}